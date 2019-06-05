package com.ufoai.platform.controller.base;

import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.constants.ParamConstants;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.MenuTree;
import com.ufoai.platform.pojo.base.ResultBean;
import com.ufoai.platform.pojo.base.UserPermissionRes;
import com.ufoai.platform.service.base.ISystemMenuService;
import com.ufoai.platform.service.base.ISystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Controller
@RequestMapping("/notes/systemMenu")
public class SystemMenuController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ISystemMenuService menuService;
    @Autowired
    ISystemUserService userService;

    @ResponseBody
    @GetMapping(value = "/permission")
    public ResultBean queryPermission() {
        ResultBean resultBean = new ResultBean();
        UserPermissionRes userPermission = new UserPermissionRes();
        SystemUser user = ShiroUtils.getUser();
        Integer type = null;
        if (user != null) {
            type = user.getType();
        }
        List<MenuTree> allMenuList;
        Set<String> permissionList;
        try {
            //超级管理员
            if (type.equals(ParamConstants.USER_TYPE_0) || type.equals(ParamConstants.USER_TYPE_1)) {
                // 查询菜单
                allMenuList = menuService.selectMenuByUser(null);
                //权限
                permissionList = userService.selectPermissionsByUser(null);
            } else {
                //普通用户
                // 查询菜单
                allMenuList = menuService.selectMenuByUser(user);
                //权限
                permissionList = userService.selectPermissionsByUser(user);
            }
            userPermission.setMenuList(allMenuList);
            userPermission.setPermissionList(permissionList);
            userPermission.setUserId(user.getId());
            userPermission.setUserName(user.getUserName());
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setSuccess(true);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setInfo(userPermission);
        } catch (Exception e) {
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
            logger.error(" SystemMenuController 查询:permission出错");
        }

        return resultBean;

    }


}
