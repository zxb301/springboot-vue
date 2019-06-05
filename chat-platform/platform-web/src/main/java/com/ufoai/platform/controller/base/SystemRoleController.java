package com.ufoai.platform.controller.base;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.annotation.Log;
import com.ufoai.platform.common.utils.MapUtil;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.entity.SystemMenu;
import com.ufoai.platform.entity.SystemRole;
import com.ufoai.platform.pojo.base.*;
import com.ufoai.platform.service.base.*;
import com.ufoai.platform.utils.DateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Controller
@RequestMapping("/notes/systemRole")
public class SystemRoleController {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入RoleService
     */
    @Autowired
    private ISystemRoleService systemRoleService;

    /**
     * 注入RoleManuService
     */
    @Autowired
    private IRoleMenuService roleMenuService;
    /**
     * 注入用户角色Service接口
     */
    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 注入用户service接口
     */
    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemMenuService systemMenuService;

    /**
     * 查询所有的角色信息之分页
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/findAllRole")
    public ResultBean findAllRole(@RequestParam Map<String, String> map) {
        ResultBean resultBean = new ResultBean();
        try {
            if (map == null) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "map");
                return resultBean;
            }
            String rows = MapUtil.get(map, "rows");
            String page = MapUtil.get(map, "page");
            int pageCurrent = 1;
            int pageSize = 10;
            if (StringUtils.isNotBlank(rows)) {
                pageSize = Integer.parseInt(rows);
            }
            if (StringUtils.isNotBlank(page)) {
                pageCurrent = Integer.parseInt(page);
            }
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("start", pageCurrent);
            queryMap.put("pageSize", pageSize);
            Page<RoleListRes> pages = systemRoleService.findAllRole(queryMap);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setInfo(pages);
        } catch (Exception e) {
            resultBean.setCode(ErrorMatrix.CODE_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
            logger.error("SystemRoleController findAllRole Exception: " + e.getMessage());
        }
        return resultBean;
    }


    /**
     * 新增角色
     *
     * @param
     * @return
     */
    // @RequiresPermissions("user:add")
    @ResponseBody
    @PostMapping("/role")
    @Log("新增角色")
    public ResultBean insertRole(@RequestBody RoleListRes roles) {
        ResultBean resultBean = new ResultBean();
        try {

            roles.setCreater(ShiroUtils.getUserId());//拿到创建人id；
            int re = systemRoleService.addRole(roles);
            Long roleId = roles.getRoleId();
            //新增角色的时候我要给该叫色绑定权限，此时，如果roles.auths==null的话，说明该叫色没有权限，反之

            SystemMenu sysm = new SystemMenu();
            sysm.setUrl("index");
            QueryWrapper entityWrapper = new QueryWrapper();
            entityWrapper.setEntity(sysm);
            SystemMenu systemMenu = systemMenuService.getOne(entityWrapper);
            RoleMenuRes rmm = new RoleMenuRes();
            rmm.setMenuId(systemMenu.getMenuId());
            rmm.setRoleId(roleId);
            roleMenuService.addRoleMenu(rmm);
            if (roles.getAuth() != null && roles.getAuth().length > 0) {//说明新增的角色是有绑定菜单
                Long[] s = roles.getAuth();
                for (int i = 0; i < s.length; i++) {
                    RoleMenuRes rm = new RoleMenuRes();//实例化角色菜单对象
                    rm.setRoleId(roleId);
                    rm.setMenuId(s[i]);
                    roleMenuService.addRoleMenu(rm);//插入角色菜单表信息
                }
            }
            if (re > 0) {
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_SAVE_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_CASE_FAIL);
            }
        } catch (Exception e) {
            logger.info("SystemRoleController insertRole Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    /**
     * 角色删除，此时要判断该叫色是否有用户使用，如果有着不能删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/role/{id}")
    @Log("删除角色")
    public ResultBean delRole(@PathVariable String id) {
        ResultBean resultBean = new ResultBean();
        try {
            RoleListRes roleListRes = new RoleListRes();//实例化对象
            roleListRes.setUpdater(ShiroUtils.getUserId());
            roleListRes.setUpdateTime(DateTimeUtil.getToday());
            if (userRoleService.findByRoleId(Long.parseLong(id)) > 0) {//大于0，说明改角色有人用
                resultBean.setMessage(ErrorMatrix.ROLE_NO_DEL);
                resultBean.setCode(ErrorMatrix.CODE_ROLE_NO_DEL);
            } else {
                roleListRes.setRoleId(Long.parseLong(id));
                roleListRes.setIsDelete("1");
                int res = systemRoleService.updateRole(roleListRes);
                if (res > 0) {//删除成功
                    resultBean.setCode(ErrorMatrix.CODE_OK);
                    resultBean.setMessage(ErrorMatrix.DB_DEL_SUCCESS);
                    resultBean.setSuccess(true);
                } else {//删除失败
                    resultBean.setMessage(ErrorMatrix.DB_DEL_FAIL);
                }
            }
        } catch (Exception e) {
            logger.error("SystemRoleController delRole Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 当你点击角色列表里面每一个角色后面的按钮--编辑菜单时候，前端你得给我提交一个roleId，根据它查询角色菜单表，
     * 查询出改roleId对应的所有菜单ids，我拼接一个用逗号间隔开的字符串给你
     *
     * @param roleId
     * @return
     */
    @ResponseBody
    @GetMapping("/role")
    public ResultBean findMenuIdsByRoleId(@RequestParam Long roleId) {
        ResultBean resultBean = new ResultBean();
        try {//拿到roleId查询角色菜单表，看其对应的menuIds
            String menuIds = roleMenuService.findMenuIdByRoleId(roleId);
            if (menuIds != null) {
                resultBean.setMessage(menuIds);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } else {
                resultBean.setMessage("特殊情况，改角色没有绑定菜单");
            }

        } catch (Exception e) {
            logger.error("SystemRoleController findMenuIdsByRoleId Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 点击角色后面的编辑菜单的时候，后端会把所有的菜单信息传给前端，并且还会吧该角色所拥有的菜单id传给前端
     *
     * @param roleId
     * @return
     */
    @ResponseBody
    @GetMapping("/authTree")
    public ResultBean getUserAuthTree(Long roleId) {
        ResultBean resultBean = new ResultBean();
        AuthInfoRes authInfo = new AuthInfoRes();
        try {
            // Long userId = ShiroUtils.getUserId();

            //查询权限树
            List<AuthTree> trees = systemUserService.queryAuthTree();
            List<Long> roleAuth;
            List<Long> openID;
            String roleName;
            if (roleId == null) {
                roleAuth = new ArrayList<>();
                openID = new ArrayList<>();
                roleName = null;
            } else {
                //查询角色拥有的权限
                //roleAuth = systemUserService.queryUserAuth(roleId);
                roleAuth = systemRoleService.findRoleAuth(roleId);
                //openID = systemUserService.queryUserAuthOpen(roleId);
                openID = systemRoleService.findRoleAuthOpen(roleId);
                //查询角色的名字
                roleName = systemRoleService.findUserNameByRoleId(roleId);
            }

            authInfo.setTrees(trees);
            authInfo.setExpanded_keys(openID);
            authInfo.setChecked_keys(roleAuth);
            authInfo.setRoleName(roleName);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setInfo(authInfo);
            resultBean.setSuccess(true);

        } catch (Exception e) {
            logger.error("SystemRoleController getUserAuthTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    /**
     * 角色修改此时，包括两层含义，一、修改改角色的名字要做唯一校验；二、修改该叫色对应的菜单;
     * 规定：此时前端必要要给我传入一个角色菜单的id字符串和添加角色的时候一样
     *
     * @param roles
     * @return
     */
    @Transactional
    @ResponseBody
    @PutMapping("/role")
    @Log("修改角色")
    public ResultBean updateRoles(@RequestBody RoleListRes roles) {
        ResultBean resultBean = new ResultBean();
        int res = 0;
        int re = 0;
        try {
            SystemRole systemRole = systemRoleService.getById(roles.getRoleId());
            if (StringUtils.isNotBlank(roles.getRoleName())) {
                systemRole.setRoleName(roles.getRoleName());
                systemRole.setUpdateTime(DateTimeUtil.getToday());
                systemRole.setUpdater(ShiroUtils.getUserId());
                systemRoleService.updateById(systemRole);
            }
            //此时，先删除该角色对应的所有菜单，再重新增加
            re = roleMenuService.delMenuByRoleId(roles.getRoleId());
            Long[] s = roles.getAuth();
            for (int i = 0; i < s.length; i++) {
                RoleMenuRes rm = new RoleMenuRes();//实例化角色菜单对象
                rm.setRoleId(roles.getRoleId());
                rm.setMenuId(s[i]);
                res = roleMenuService.addRoleMenu(rm);
            }
            SystemMenu sysm = new SystemMenu();
            sysm.setUrl("index");
            QueryWrapper entityWrapper = new QueryWrapper();
            entityWrapper.setEntity(sysm);
            SystemMenu systemMenu = systemMenuService.getOne(entityWrapper);
            RoleMenuRes rmm = new RoleMenuRes();
            rmm.setMenuId(systemMenu.getMenuId());
            rmm.setRoleId(roles.getRoleId());
            roleMenuService.addRoleMenu(rmm);

            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);


        } catch (Exception e) {
            logger.error("SystemRoleController updateRoles Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    /**
     * 启用禁用
     *
     * @param roles
     * @return
     */
    @ResponseBody
    @PutMapping("/startAndClose")
    public ResultBean startAndClose(@RequestBody RoleListRes roles) {
        ResultBean resultBean = new ResultBean();
        try {
            roles.setUpdater(ShiroUtils.getUserId());
            roles.setUpdateTime(DateTimeUtil.getToday());
            int re = systemRoleService.updateRole(roles);
            if (re > 0) {//操作成功
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_FAIL);
            }

        } catch (Exception e) {
            logger.error("SystemUserController edit Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 角色名字的唯一校验;这里我是把增加的时候和修改的时候合并在一起进行验证
     *
     * @param roleId
     * @param roleName
     * @return
     */
    @ResponseBody
    @GetMapping("/checkRoleName")
    public boolean checkRoleName(Long roleId, String roleName) {
        try {
            if (roleId != null) {//说明此时是修改操作
                if (systemRoleService.checkRoleName(roleId, roleName) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (systemRoleService.checkRoleName(null, roleName) == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error("SystemUserController checkCode Exception: " + e.getMessage());
        }
        return false;
    }

    /**
     * 查询所有的角色信息返回给前端，目的是在添加用户的时候产生角色的下拉框
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/roleList")
    public ResultBean selectRole() {
        ResultBean resultBean = new ResultBean();
        try {
            List<RoleListRes> rs = systemRoleService.selectRoleList();
            if (rs != null && rs.size() > 0) {
                resultBean.setInfo(rs);
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            }
        } catch (Exception e) {
            logger.error("SystemUserController edit Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

}
