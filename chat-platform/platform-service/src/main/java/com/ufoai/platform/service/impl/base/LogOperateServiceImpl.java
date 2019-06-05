package com.ufoai.platform.service.impl.base;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.annotation.Log;
import com.ufoai.platform.entity.LogOperate;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.mapper.base.LogOperateMapper;
import com.ufoai.platform.mapper.base.SystemRoleMapper;
import com.ufoai.platform.mapper.base.SystemUserMapper;
import com.ufoai.platform.pojo.base.LogOperateRes;
import com.ufoai.platform.pojo.base.PageParamReq;
import com.ufoai.platform.pojo.base.RoleListRes;
import com.ufoai.platform.pojo.base.UserAuthReq;
import com.ufoai.platform.service.base.ILogOperateService;
import com.ufoai.platform.utils.DateTimeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-16
 */
@Service
public class LogOperateServiceImpl extends ServiceImpl<LogOperateMapper, LogOperate> implements ILogOperateService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogOperateMapper logOperateMapper;

    @Autowired
    private SystemUserMapper systemUserMapper;


    @Autowired
    private SystemRoleMapper systemRoleMapper;



    @Override
    public Page<LogOperateRes> selectList(PageParamReq param) {
        Page<LogOperateRes> page = new Page(param.getPage(), param.getRows());
        List<LogOperateRes> list = logOperateMapper.selectLogOperateList(page, param);
        page.setRecords(list);
        return page;
    }

    @Async
    public void saveLog(ProceedingJoinPoint joinPoint, long time, SystemUser sUser, SystemUser currUser, String ip) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogOperate sysLog = new LogOperate();
        Log syslog = method.getAnnotation(Log.class);
        Object[] args = joinPoint.getArgs();
        if (syslog != null) {
            // 请求的参数
            try {
                switch (syslog.value()) {
                    case "新增用户":
                        UserAuthReq p1 = (UserAuthReq) args[0];
                        sysLog.setOperation("创建了【" + p1.getUserName() + "】的账户");
                        break;
                    case "修改用户":
                        UserAuthReq p2 = (UserAuthReq) args[0];
                        sysLog.setOperation("修改了【" + p2.getUserName() + "】的账户");
                        break;
                    case "用户状态":
                        UserAuthReq p3 = (UserAuthReq) args[0];
                        SystemUser sys = systemUserMapper.selectById(p3.getId());
                        if (p3.getStatus() == 1) {
                            sysLog.setOperation("禁用了【" + sys.getUserName() + "】的账户");
                        } else {
                            sysLog.setOperation("启用了【" + sys.getUserName() + "】的账户");
                        }
                        break;
                    case "重置密码":
                        Map params = (Map) args[0];
                        String userId = params.get("userId") + "";
                        SystemUser systemUser = systemUserMapper.selectById(Long.valueOf(userId));
                        sysLog.setOperation("重置了【" + systemUser.getUserName() + "】的账户密码");
                        break;
                    case "删除用户":
                        Long uId = (Long) args[0];
                        SystemUser user = systemUserMapper.selectById(Long.valueOf(uId));
                        sysLog.setOperation("删除了【" + user.getUserName() + "】的账户");
                        break;

                    case "登录":
                        sysLog.setOperation("登录系统");
                        break;
                    case "退出":
                        sysLog.setOperation("退出系统");
                        break;

                    case "新增角色":
                        RoleListRes role =(RoleListRes)args[0];
                        sysLog.setOperation("新增角色【" + role.getRoleName() + "】");
                        break;
                    case "删除角色":
                        Long roleId =(Long)args[0];
                        sysLog.setOperation("删除角色【" + systemRoleMapper.selectById(roleId).getRoleName() + "】");
                        break;
                    case "修改角色":
                        RoleListRes role2 =(RoleListRes)args[0];
                        sysLog.setOperation("修改角色【" + role2.getRoleName() + "】");
                        break;
                    default:
                        sysLog.setOperation("");
                }


            } catch (Exception e) {
                logger.error(e.toString());
            }

        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        try {
            String params = JSONObject.toJSONString(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {
            logger.error(e.toString());
        }

        // 设置IP地址
        sysLog.setIp(ip);
        // 用户名
        if (null == currUser) {
            if(sUser!=null){
                sysLog.setUserId(sUser.getId());
                sysLog.setUsername(sUser.getUserName());
            }
           else if (null != sysLog.getParams()) {
                sysLog.setUserId(-1L);
                sysLog.setUsername(sysLog.getParams());
            } else {
                sysLog.setUserId(-1L);
                sysLog.setUsername("获取用户信息为空");
            }
        } else {
            sysLog.setUserId(currUser.getId());
            sysLog.setUsername(currUser.getUserName());
        }
        sysLog.setTime((int) time);
        // 系统当前时间

        sysLog.setGmtCreate(DateTimeUtil.getToday() + "");
        // 保存系统日志
        logOperateMapper.insert(sysLog);

    }
}
