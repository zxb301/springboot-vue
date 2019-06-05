package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.SystemRole;
import com.ufoai.platform.mapper.base.SystemRoleMapper;
import com.ufoai.platform.pojo.base.RoleListRes;
import com.ufoai.platform.service.base.ISystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {
    @Autowired
    SystemRoleMapper systemRoleMapper;

    @Override
    public Set<String> findAllRoleName() {
        List<String> roles = systemRoleMapper.findAllRoleName();
        Set<String> roleSet = new HashSet<>();
        for (String role : roles) {
            if (StringUtils.isNotBlank(role)) {
                roleSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return roleSet;
    }

    @Override
    public Set<String> findRoleNameByUserId(Long userId) {
        List<String> roles = systemRoleMapper.findRoleNameByUserId(userId);
        Set<String> roleSet = new HashSet<>();
        for (String role : roles) {
            if (StringUtils.isNotBlank(role)) {
                roleSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return roleSet;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public int addRole(RoleListRes role) {
       int re= systemRoleMapper.addRole(role);
        return re;
    }

    /**
     * 修改角色和删除
     * @param role
     * @return
     */
    @Override
    public int updateRole(RoleListRes role) {
        return systemRoleMapper.updateRole(role);
    }

    /**
     * 根据roleName查询有没有存在，新增的时候校验
     * @param roleName
     * @return
     */
    @Override
    public int findByRoleName(String roleName) {
        return systemRoleMapper.findByRoleName(roleName);
    }

    /**
     * 根据roleId查询出对应的roleName
     * @param roleId
     * @return
     */
    @Override
    public String findUserNameByRoleId(Long roleId) {
        return systemRoleMapper.findUserNameByRoleId(roleId);
    }

    /**
     * 增加和修改的时候对userName进行唯一校验
     * @param roleId
     * @param RoleName
     * @return
     */
    @Override
    public int checkRoleName(Long roleId, String RoleName) {
        int re=0;

        if (roleId!=null){//此乃是修改的操作
          String roleName = findUserNameByRoleId(roleId);
           if ( roleName.equals(RoleName)){
               re=0;//说明用户就没有修改嘛
               }else {
               re = findByRoleName(RoleName);//大于0的话说明修改的时候有重复
           }
        }else {//增加的时候对userName进行唯一验证
           re =findByRoleName(RoleName);//大于0的话说明增加的时候有重复
        }
        return re;
    }

    /**
     * 查询出所有的角色信息返回给前端，在添加用户的时候方便出现角色下拉框
     * @return
     */
    @Override
    public List<RoleListRes> selectRoleList() {
        return systemRoleMapper.selectRoleList();
    }

    /**
     * 查询所有的角色信息
     * @param map
     * @return
     */
    @Override
    public Page<RoleListRes> findAllRole(Map map) {
        Page<RoleListRes>   page = new Page((int) map.get("start"), (int) map.get("pageSize"));
        List<RoleListRes> list= systemRoleMapper.findAllRole( page,map);
        page.setRecords(list);
        return page;
    }

    /**
     * 查询该角色拥有的权限id
     * @param roleId
     * @return
     */
    @Override
    public List<Long> findRoleAuth(Long roleId) {
        return systemRoleMapper.findRoleAuth(roleId);
    }

    /**
     * 查询权限的parentId
     * @param roleId
     * @return
     */
    @Override
    public List<Long> findRoleAuthOpen(Long roleId) {
        return systemRoleMapper.findRoleAuthOpen(roleId);
    }

    /**
     * 把数组转换成字符串
     * @param a
     * @param b
     * @return
     */
    @Override
    public String getStrings(String a,Long[] b) {
        for (int i=0;i<b.length;i++){//遍历数组，再转化成以逗号拼接的字符串
            a+=b[i]+",";
        }
        return a.substring(0,a.length()-1);
    }


}
