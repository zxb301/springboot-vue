package com.ufoai.platform.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.annotation.Log;
import com.ufoai.platform.common.utils.MapUtil;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.constants.ParamConstants;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.*;
import com.ufoai.platform.service.base.ISystemUserService;
import com.ufoai.platform.service.base.IUserRoleService;
import com.ufoai.platform.utils.BeanUtils;
import com.ufoai.platform.utils.DateTimeUtil;
import com.ufoai.platform.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Controller
@RequestMapping("/notes/systemUser")
public class SystemUserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISystemUserService systemUserService;
    /**
     * 注入用户叫色mapper
     */
    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 用户列表查询
     *
     * @param map
     * @return
     */
    // @RequiresPermissions("user:list")
    @ResponseBody
    @GetMapping("/users")
    public ResultBean userList(@RequestParam Map map) {
        ResultBean resultBean = new ResultBean();
        try {
            if (map == null) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "map");
                return resultBean;
            }
            String rows = MapUtil.get(map, "rows");
            String page = MapUtil.get(map, "page");
            String userName= MapUtil.get(map,"userName");
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
            if (StringUtils.isNoneBlank(userName)){
                queryMap.put("userName",userName);
            }else {
                queryMap.put("userName",null);
            }
            Page<UserListRes> pages = systemUserService.selectUserList(queryMap);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setInfo(pages);
        } catch (Exception e) {
            logger.error("SystemUserController userList Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @Log("新增用户")
    @RequiresPermissions("user:add")
    @ResponseBody
    @PostMapping("/user")
    public ResultBean insert(@RequestBody @Validated UserAuthReq authReq) {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser user = new SystemUser();
            BeanUtils.copyProperties(authReq, user);
            user.setCreater(ShiroUtils.getUserId() + "");
            user.setCreateTime(DateTimeUtil.getToday());
            user.setType(ParamConstants.USER_TYPE_2);
            user.setPassword(Md5Util.toMD5(ParamConstants.USER_PASSWORD));
            int a = systemUserService.insertUser(user, authReq);
            if (a > 0) {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_SUCCESS);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_FAIL);
            }
        } catch (Exception e) {
            logger.info("SystemUserController insert Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * tyz新增用户
     * @param userListRes
     * @return
     */
    @Log("新增用户")
    @PostMapping("/addUser")
    @ResponseBody
    public ResultBean addUser(@RequestBody UserListRes userListRes){
        ResultBean resultBean = new ResultBean();
        try {
            userListRes.setCreater(ShiroUtils.getUserId()+"");
            userListRes.setCreateTime(DateTimeUtil.getToday());
            userListRes.setPassword(Md5Util.toMD5(ParamConstants.USER_PASSWORD));
            userListRes.setType(Long.valueOf(ParamConstants.USER_TYPE_2));
            int  re= systemUserService.addUser(userListRes);//新增成功之后会把新的id给我带回来
            if (re > 0) {
                //实例化用户角色
                UserRoleRes userRoleRes=new UserRoleRes();
                userRoleRes.setRoleId(userListRes.getRoleId());
                userRoleRes.setUserId(userListRes.getId());
                int res=userRoleService.addUserRole(userRoleRes);//此时会向用户角色表里面增加一条信息
                if (res>0){
                    resultBean.setSuccess(true);
                    resultBean.setMessage(ErrorMatrix.DB_SAVE_SUCCESS);
                    resultBean.setCode(ErrorMatrix.CODE_OK);
                }else {
                    resultBean.setMessage(ErrorMatrix.DB_SAVE_FAIL);
                }
            } else {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_FAIL);
            }
        }catch (Exception e) {
            logger.info("SystemUserController addUser Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * tyz修改一个用户信息
     * @param userListRes
     * @return
     */
    @Log("修改用户")
    @PutMapping("/updateUser")
    @ResponseBody
    public  ResultBean updateUsers(@RequestBody UserListRes userListRes){
        ResultBean resultBean = new ResultBean();
        try {
            userListRes.setUpdater(ShiroUtils.getUserId() + "");
            userListRes.setUpdateTime(DateTimeUtil.getToday());
            int b=  systemUserService.updateUsers(userListRes);
            if (b > 0) {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setSuccess(true);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_FAIL);
            }
        }catch (Exception e) {
            logger.error("SystemUserController updateUsers Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    @Log("修改用户")
    @RequiresPermissions("user:edit")
    @ResponseBody
    @PutMapping("/user")
    public ResultBean edit(@RequestBody @Validated UserAuthReq authReq) {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser user = new SystemUser();
            BeanUtils.copyProperties(authReq, user);
            user.setUpdater(ShiroUtils.getUserId() + "");
            user.setUpdateTime(DateTimeUtil.getToday());
            int b = systemUserService.updateUserById(user, authReq);
            if (b > 0) {
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
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

    @Log("用户状态")
    @RequiresPermissions("user:edit")
    @ResponseBody
    @PutMapping("/userStatus")
    public ResultBean userStatus(@RequestBody UserAuthReq authReq) {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser user = new SystemUser();
            user.setUpdater(ShiroUtils.getUserId() + "");
            BeanUtils.copyProperties(authReq, user);
            user.setUpdateTime(DateTimeUtil.getToday());
            boolean b = systemUserService.updateById(user);
            if (b) {
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_FAIL);
            }
        } catch (Exception e) {
            logger.error("SystemUserController userStatus Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @Log("重置密码")
    @RequiresPermissions("user:reset_password")
    @ResponseBody
    @PutMapping("/resetPassword")
    public ResultBean resetPassword(@RequestBody Map map) {
        ResultBean resultBean = new ResultBean();
        try {
            if (map == null) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "map");
                return resultBean;
            }
            String userId = MapUtil.get(map, "userId");
            if (StringUtils.isBlank(userId)) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "userId");
                return resultBean;
            }
            map.put("password", ParamConstants.USER_PASSWORD);
            int a = systemUserService.resetPassword(map);
            if (a > 0) {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            }

        } catch (Exception e) {
            logger.error("SystemUserController resetPassword Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @Log("修改密码")
    @ResponseBody
    @PutMapping("/editPassword")
    public ResultBean editPassword(@RequestBody Map map) {
        ResultBean resultBean = new ResultBean();
        try {
            /**
             * 参数效验
             */
            if (map == null) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "map");
                return resultBean;
            }
            String oldPas = MapUtil.get(map, "oldPas");
            if (StringUtils.isBlank(oldPas)) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "oldPas");
                return resultBean;
            }

            String newPas = MapUtil.get(map, "newPas");
            if (StringUtils.isBlank(newPas)) {
                resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
                resultBean.setMessage(ErrorMatrix.INVALID_PARAMTER + "newPas");
                return resultBean;
            }
            Long userId = ShiroUtils.getUserId();
            map.put("userId", userId);
            //修改密码
            int a = systemUserService.editPassword(map);
            if (a == 0) {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_PASSWORD_FAIL);
                resultBean.setCode(ErrorMatrix.CODE_FAILED);
            }
            if (a >= 1) {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            }

        } catch (Exception e) {
            logger.error("SystemUserController resetPassword Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 详情查询
     *
     * @param id
     * @return
     */
/*    @ResponseBody
    @GetMapping("/user/{id}")
    public ResultBean getUserById(@PathVariable("id") Long id) {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser systemUser = systemUserService.findByUserId(id);

            if (systemUser != null) {
                UserInfoRes user = new UserInfoRes();
                BeanUtils.copyProperties(systemUser, user);
                resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setInfo(user);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_QUERY_NO_DATA);
                resultBean.setCode(ErrorMatrix.CODE_NO_DATA);
            }
        } catch (Exception e) {
            logger.error("SystemUserController getUserById Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }*/
    //tyz用户详情查询
    @ResponseBody
    @GetMapping("/user/{id}")
    public ResultBean findUserById(@PathVariable("id") Long id){
        ResultBean resultBean = new ResultBean();
        try {
            UserListRes  userListRes  = systemUserService.findUserById(id);
            if (userListRes!=null){
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
                resultBean.setInfo(userListRes);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            }else {
                resultBean.setMessage(ErrorMatrix.DB_QUERY_NO_DATA);
                resultBean.setCode(ErrorMatrix.CODE_NO_DATA);
            }

        }catch (Exception e) {
            logger.error("SystemUserController findUserById Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    @Log("删除用户")
    @RequiresPermissions("user:del")
    @ResponseBody
    @DeleteMapping("/user/{id}")
    public ResultBean deleteUser(@PathVariable("id") Long id) {
        ResultBean resultBean = new ResultBean();
        try {
            Map map = new HashMap();
            map.put("id", id);
            map.put("delId", ShiroUtils.getUserId() + "");
            map.put("delTime", DateTimeUtil.getToday());
            int a = systemUserService.deleteUser(map);
            if (a > 0) {
                resultBean.setMessage(ErrorMatrix.DB_DEL_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setSuccess(true);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_DEL_FAIL);
            }
        } catch (Exception e) {
            logger.error("SystemUserController deleteUser Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 用户权限树查询
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/authTree")
    public ResultBean getUserAuthTree(Long userId) {
        ResultBean resultBean = new ResultBean();
        AuthInfoRes authInfo = new AuthInfoRes();
        try {
            // Long userId = ShiroUtils.getUserId();

            //查询权限树
            List<AuthTree> trees = systemUserService.queryAuthTree();
            List<Long> userAuth;
            List<Long> openID;
            if (userId == null) {
                userAuth = new ArrayList<>();
                openID = new ArrayList<>();
            } else {
                //查询用户拥有的权限
                userAuth = systemUserService.queryUserAuth(userId);
                openID = systemUserService.queryUserAuthOpen(userId);
            }

            authInfo.setTrees(trees);
            authInfo.setExpanded_keys(openID);
            authInfo.setChecked_keys(userAuth);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setInfo(authInfo);
            resultBean.setSuccess(true);

        } catch (Exception e) {
            logger.error("SystemUserController getUserAuthTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 用户权限树查询
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/editAuthTree")
    public ResultBean editAuthTree(@RequestBody @Validated UserAuthReq authReq) {
        ResultBean resultBean = new ResultBean();
        AuthInfoRes authInfo = new AuthInfoRes();
        try {
            boolean isOk = systemUserService.editUserAuth(authReq);
            if (isOk) {
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setInfo(authInfo);
                resultBean.setSuccess(true);
            }
        } catch (Exception e) {
            logger.error("SystemUserController editAuthTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 账号去重查询
     *
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/checkCode")
    public boolean checkCode(Long userId, String code) {
        try {
            int a;
            if (userId == null) {
                a = systemUserService.checkCode(null, code);
            } else {
                a = systemUserService.checkCode(userId, code);
            }
            if (a == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("SystemUserController checkCode Exception: " + e.getMessage());

        }
        return false;
    }

    /**
     * 前端获取用户信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/getUser")
    public ResultBean getUser() {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser systemUser = ShiroUtils.getUser();
            UserInfoRes user = new UserInfoRes();
            BeanUtils.copyProperties(systemUser, user);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(user);

        } catch (Exception e) {
            logger.error("SystemUserController getUser Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 查询警号or姓名是否正确
     *
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/checkNo")
    public boolean checkNo(String no, String name) {
        try {
            SystemUser systemUser = new SystemUser();
            if (StringUtils.isNotBlank(no)) {
                systemUser.setCode(no);
            }
            if (StringUtils.isNotBlank(name)) {
                systemUser.setUserName(name);
            }
            systemUser.setIsDelete(0);
            QueryWrapper<SystemUser> entityWrapper = new QueryWrapper(systemUser);
            SystemUser user = systemUserService.getOne(entityWrapper);
            if (null != user) {
                return true;
            }
        } catch (Exception e) {
            logger.error("SystemUserController checkNo Exception: " + e.getMessage());

        }
        return false;
    }

    @ResponseBody
    @GetMapping("/getUserByNo")
    public ResultBean getUserByNo(String no) {
        ResultBean resultBean = new ResultBean();
        try {
            SystemUser systemUser = new SystemUser();
            systemUser.setCode(no);
            systemUser.setIsDelete(0);
            QueryWrapper<SystemUser> entityWrapper = new QueryWrapper(systemUser);
            SystemUser user = systemUserService.getOne(entityWrapper);
            if (null != user) {
                UserInfoRes user1 = new UserInfoRes();
                user1.setCode(user.getCode());
                user1.setUserName(user.getUserName());
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setInfo(user1);
                resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            } else {
                resultBean.setInfo(null);
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.DB_QUERY_NO_DATA);
                resultBean.setCode(ErrorMatrix.CODE_NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SystemUserController getUserByNo Exception: " + e.getMessage());

        }
        return resultBean;
    }
 /*   //用户登录的时候根据用户id查询出对应的用户所有权限
    public  ResultBean  findAllAuthByUser(){
        ResultBean resultBean = new ResultBean();



        return resultBean;
    }*/
}
