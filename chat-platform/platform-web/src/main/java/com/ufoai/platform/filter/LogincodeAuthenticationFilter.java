package com.ufoai.platform.filter;

import com.alibaba.fastjson.JSONObject;
import com.ufoai.platform.config.ApplicationContextRegister;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.pojo.base.ResultBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class LogincodeAuthenticationFilter extends AccessControlFilter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //如果要想取得在session中出现的验证码

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextRegister.getApplicationContext().getBean("redisTemplate");
        String rand = redisTemplate.opsForValue().get("verify_code:"+token) + "";
        String code = httpServletRequest.getParameter("verifyCode");
        ResultBean resultBean = new ResultBean();
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out;
        if (rand == null || code == null || "".equals(rand) || "".equals(code)) {
            out = response.getWriter();
            resultBean.setMessage(ErrorMatrix.LOG_CODE_FAIL);
            resultBean.setCode(ErrorMatrix.CODE_FAILED_VERFIFY);
            String str = JSONObject.toJSONString(resultBean);
            out.print(str);
            out.flush();
            out.close();
            return false;
        } else {
            if (!code.equalsIgnoreCase(rand)) {
                out = response.getWriter();
                resultBean.setMessage(ErrorMatrix.LOG_CODE_ERROR);
                resultBean.setCode(ErrorMatrix.CODE_FAILED_VERFIFY);
                String str = JSONObject.toJSONString(resultBean);
                out.print(str);
                out.flush();
                out.close();
                return false;
            }
        }
        redisTemplate.delete("verify_code:"+token);
        return true;
    }
}
