package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.SystemMenu;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.entity.UserRole;
import com.ufoai.platform.mapper.base.*;
import com.ufoai.platform.pojo.base.AuthListRes;
import com.ufoai.platform.pojo.base.AuthTree;
import com.ufoai.platform.pojo.base.UserAuthReq;
import com.ufoai.platform.pojo.base.UserListRes;
import com.ufoai.platform.service.base.ISystemUserService;
import com.ufoai.platform.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-08
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemMenuMapper systemMenuMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Page<UserListRes> selectUserList(Map map) {
       Page<UserListRes> page = new Page((int) map.get("start"), (int) map.get("pageSize"));
       // Page<UserListRes> page =new  Page(param.getPage(), param.getRows());
        List<UserListRes> list = systemUserMapper.selectUserList(page, map);
       /* for (UserListRes user : list) {
            List<String> authName = systemUserMapper.selectUserAuthName(user.getId());
            if (authName != null && authName.size() > 0) {
                StringBuffer str = new StringBuffer();
                for (String name : authName) {
                    str.append(name).append(";");
                }
                user.setAuth(str.substring(0, str.length() - 1).toString());
            }


        }*/
        page.setRecords(list);
        return page;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertUser(SystemUser user, UserAuthReq authReq) {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return 1;
    }

    @Override
    // @CachePut(value = "user", key = "'userId_'+#id")
    public int updateUserById(SystemUser user, UserAuthReq authReq) {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return 1;
    }

    @Override
    // @CacheEvict(value = "user", key = "'userId_'+#id")
    public int deleteUser(Map map) {
        return systemUserMapper.deleteUserById(map);
    }

    @Override
    public List<AuthTree> queryAuthTree() {
        List<SystemMenu> listMenuTree;
        QueryWrapper<SystemMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete ", "0").eq("is_show","1");
        listMenuTree = systemMenuMapper.selectList(wrapper);
        List<AuthTree> menuList = new ArrayList<>();

        // 先找到所有的一级菜单
        for (int i = 0; i < listMenuTree.size(); i++) {
            // 一级菜单没有parentId
            if (listMenuTree.get(i).getParentId().longValue() == 0) {
                AuthTree menuTree = new AuthTree();
                menuTree.setId(listMenuTree.get(i).getMenuId());
                menuTree.setLabel(listMenuTree.get(i).getName());
                menuTree.setIsNode(listMenuTree.get(i).getIsNode());
                menuList.add(menuTree);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (AuthTree menu : menuList) {
            menu.setChildren(getChild(menu.getId(), listMenuTree));//目前我拿到了所有一级菜单的id集合了，还能拿到所有的菜单信息
        }

        return menuList;
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<AuthTree> getChild(Long id, List<SystemMenu> rootMenu) {
        // 子菜单
        List<AuthTree> childList = new ArrayList<>();
        for (SystemMenu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId() != 0) {
                if (menu.getParentId().equals(id) ) {//因为你的一级菜单id都是二级菜单的parent_id,所以用parent_id作比较
                    AuthTree menuTree = new AuthTree();
                    menuTree.setId(menu.getMenuId());
                    menuTree.setLabel(menu.getName());
                    menuTree.setIsNode(menu.getIsNode());
                    childList.add(menuTree);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
 /*       for (AuthTree menu : childList) {// 没有url子菜单还有子菜单
            //if (menu.getIsNode() == 0) {
                // 递归
                menu.setChildren(getChild(menu.getId(), rootMenu));
            //}
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }*/
        if(childList==null && childList.size()<1 ){
            return null;

        }else{
            for (AuthTree menu : childList){
                menu.setChildren(getChild(menu.getId(), rootMenu));
            }

        }

        return childList;
    }




    @Override
    // @Cacheable(value = "user", key = "'userId_'+#id")
    public SystemUser findByUserId(Long id) {
        return systemUserMapper.selectById(id);
    }

    @Override
    public Set<String> selectPermissionsByUser(SystemUser user) {
        List<String> perms;
        if (user == null) {
            perms = systemUserMapper.selectAllPermissions();
        } else {
            perms = systemUserMapper.selectPermissionsByUser(user.getId());
        }
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Long> queryUserAuth(Long userId) {
        return systemUserMapper.queryUserAuth(userId);
    }

    @Override
    public List<Long> queryUserAuthOpen(Long userId) {
        return systemUserMapper.queryUserAuthOpen(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean editUserAuth(UserAuthReq authReq) {
        try {
            //删除所有权限
            Integer del = systemUserMapper.deleteUserAuth(authReq.getId());
            //新增所有权限
            Integer add = systemUserMapper.addUserAuth(authReq.getId(), authReq.getAuth());
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    @Override
    public int resetPassword(Map map) {
        SystemUser systemUser = new SystemUser();
        systemUser.setPassword(Md5Util.toMD5(map.get("password") + ""));
        systemUser.setId(Long.valueOf(map.get("userId") + ""));
        int a = systemUserMapper.updateById(systemUser);
        return a;
    }

    @Override
    public int editPassword(Map map) {
        SystemUser user = new SystemUser();
        user.setId(Long.valueOf(map.get("userId") + ""));
        user.setPassword((map.get("oldPas") + "").toUpperCase());
        SystemUser systemUser = systemUserMapper.selectOne(new QueryWrapper<>(user));
        if (systemUser == null) {
            return 0;
        } else {
            systemUser.setPassword((map.get("newPas") + "").toUpperCase());
            systemUserMapper.updateById(systemUser);
            return 1;
        }
    }

    @Override
    public int checkCode(Long userId, String code) {
        if (userId == null) {
            List<SystemUser> userList = systemUserMapper.selectList(
                    new QueryWrapper<SystemUser>().eq("code", code).
                            eq("is_delete", 0)
            );
            if (userList.size() >= 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            List<SystemUser> userList = systemUserMapper.selectList(
                    new QueryWrapper<SystemUser>().eq("code={0}", code).ne("id", userId).
                            eq("is_delete", 0)
            );
            if (userList.size() >= 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 根据用户id查询出对应的角色名字
     * @param userId
     * @return
     */
    @Override
    public String findRoleNameByUserId(Long userId) {
        return systemUserMapper.findRoleNameByUserId(userId);
    }

    /**
     * 新增一个用户
     * @param userListRes
     * @return
     */
    @Override
    public int addUser(UserListRes userListRes) {
        return systemUserMapper.addUser(userListRes);
    }

    /**
     * 修改一个用户信息
     * @param userListRes
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateUsers(UserListRes userListRes) {
        try {
            //用户信息修改
            systemUserMapper.updateUsers(userListRes);
            //用户角色修改
            UserRole userRole = new UserRole();
            userRole.setRoleId(userListRes.getRoleId());
            int a=userRoleMapper.update(userRole,new QueryWrapper<UserRole>().eq("user_id",userListRes.getId()));
            if(a < 1){
                userRole.setUserId(userListRes.getId());
                userRoleMapper.insert(userRole);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    /**
     * 根据id查询出对应的用户信息
     * @param id
     * @return
     */
    @Override
    public UserListRes findUserById(Long id) {
        return systemUserMapper.findUserById(id);
    }

    /**
     * 登陆的时候根据用户id查询出对应的用户权限
     * @param map
     * @return
     */
    @Override
    public List<AuthListRes> findUserAllAuth(Map map) {
        return systemUserMapper.findUserAllAuth(map);
    }
}
