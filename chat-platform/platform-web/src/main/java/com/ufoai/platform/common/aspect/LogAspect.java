package com.ufoai.platform.common.aspect;



import com.ufoai.platform.common.utils.HttpContextUtils;
import com.ufoai.platform.common.utils.IPUtils;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.service.base.ILogOperateService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    ILogOperateService logOperateService;

    @Pointcut("@annotation(com.ufoai.platform.annotation.Log)")
    public void logPointCut() {
    }
//
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        SystemUser sUser = ShiroUtils.getUser();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        SystemUser currUser = ShiroUtils.getUser();
        //异步保存日志
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //logOperateService.saveLog(point, time,sUser,currUser, IPUtils.getIpAddr(request));
        return result;
    }
}
