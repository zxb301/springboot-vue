package com.ufoai.platform.controller.base;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private DefaultKaptcha producer;


    @Value("${server.session.timeout}")
    private Long serverSessionTimeout;



    /**
     * 获取验证码
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginCode")
    @ResponseBody
    public ResultBean image() throws IOException {
        ResultBean resultBean = new ResultBean();
       /* // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream;
        BufferedImage image = producer.createImage(text);
        outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Subject subject = SecurityUtils.getSubject();
        String sessionId = (String) subject.getSession().getId();
       // redisTemplate.opsForValue().set("verify_code:" + sessionId, text);
        //redisTemplate.expire("verify_code:" + sessionId, 300, TimeUnit.SECONDS);
        // 对字节数组Base64编码
        Map map = new HashMap();
        map.put("img", Base64.getEncoder().encodeToString(outputStream.toByteArray()));
        map.put("sessionId", sessionId);
        resultBean.setInfo(map);
        resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);*/
        resultBean.setSuccess(true);
        resultBean.setCode(ErrorMatrix.CODE_OK);
        return resultBean;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultBean login(String userCode, SystemUser user) {
        ResultBean resultBean = new ResultBean();
        Map<String, String> map = new HashMap();
        if (!StringUtils.isNotBlank(userCode) || !StringUtils.isNotBlank(user.getPassword())) {
            resultBean.setMessage(ErrorMatrix.PARAMS_ERROR);
            resultBean.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userCode, user.getPassword().toUpperCase());
            try {
                subject.login(usernamePasswordToken);
                SessionsSecurityManager securityManager = (SessionsSecurityManager) SecurityUtils.getSecurityManager();
                DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
                Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
                if (subject.isAuthenticated()) {
                    for (Session session : sessions) {
                        if(userCode.equals(session.getAttribute("loginedUser"))){
                         session.setTimeout(0);
                         sessionManager.getSessionDAO().delete(session);
                       }
                    }
                }
                SecurityUtils.getSubject().getSession().setTimeout(serverSessionTimeout);
                subject.getSession().setAttribute("loginedUser",userCode);
                String sessionId = (String) subject.getSession().getId();
                map.put("sessionId", sessionId);
                map.put("userName", ShiroUtils.getUserName());
                map.put("type", ShiroUtils.getUser().getType()+"");

                resultBean.setInfo(map);
                resultBean.setSuccess(true);
                resultBean.setMessage(ErrorMatrix.LOG_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
            } catch (UnknownAccountException e) {
                throw new UnknownAccountException();
            } catch (IncorrectCredentialsException e) {
                throw new IncorrectCredentialsException();
            } catch (DisabledAccountException e) {
                throw new DisabledAccountException();
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw e;
            }
        }
        return resultBean;
    }

    @RequestMapping(value = "/unAuth")
    @ResponseBody
    public ResultBean unAuth() {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage(ErrorMatrix.LOG_FAIL);
        resultBean.setCode(ErrorMatrix.CODE_LOGIN_FAILED);
        return resultBean;
    }

    @RequestMapping(value = "/index")
    @ResponseBody
    public ResultBean index() {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setMessage(ErrorMatrix.LOG_SUCCESS);
        return resultBean;
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public ResultBean logout() {
        ShiroUtils.logout();
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(true);
        resultBean.setMessage(ErrorMatrix.LOG_LOGOUT);
        return resultBean;
    }
}
