package com.ufoai.platform.common.utils;


import com.ufoai.platform.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static SystemUser getUser() {
        Object object = getSubjct().getPrincipal();
        return (SystemUser) object;
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static String getUserName() {
        return getUser().getUserName();
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
    public static  Collection<Session> getSessions() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return sessions;
    }
}
