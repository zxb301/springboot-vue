package com.ufoai.platform.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ufoai.platform.constants.ParamConstants;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.service.base.ISystemRoleService;
import com.ufoai.platform.service.base.ISystemUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Lazy
    private ISystemRoleService roleService;
    @Autowired
    @Lazy
    private ISystemUserService userService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SystemUser systemUser = new SystemUser();
        systemUser.setCode(name);
        systemUser.setPassword(password);
        systemUser.setIsDelete(0);
        // 从数据库获取对应用户名密码的用户
        SystemUser user = userService.getOne(new QueryWrapper<>(systemUser));
        if (user != null) {
            // 用户为禁用状态
            if (user.getStatus() != 0) {
                throw new DisabledAccountException();
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof SystemUser) {
            SystemUser userLogin = (SystemUser) principal;
            Set<String> roles;
            Set<String> permissions;
            if (userLogin.getType().equals(ParamConstants.USER_TYPE_0) ||userLogin.getType().equals(ParamConstants.USER_TYPE_1) ) {
                roles = roleService.findAllRoleName();
                permissions = userService.selectPermissionsByUser(null);
            } else {
                roles = roleService.findRoleNameByUserId(userLogin.getId());
                permissions = userService.selectPermissionsByUser(userLogin);
            }
            authorizationInfo.addRoles(roles);
            authorizationInfo.addStringPermissions(permissions);
        }
        logger.info("---- 获取到以下权限 ----");
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}
