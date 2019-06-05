package com.ufoai.platform.filter;

import com.alibaba.fastjson.JSONObject;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.pojo.base.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class AccessTokenFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (subject.getPrincipal() == null) {
            if(StringUtils.isNotBlank(httpServletRequest.getHeader("token"))){
                ResultBean resultBean = new ResultBean();
                response.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = response.getWriter();
                resultBean.setMessage("您已经在其他地方登录，请重新登录！");
                resultBean.setCode(ErrorMatrix.CODE_NO_DATA);
                String str = JSONObject.toJSONString(resultBean);
                out.print(str);
                out.flush();
                out.close();
                return false;
            }
            WebUtils.issueRedirect(request, response, "/unAuth");
            return  false;

        }
        return true;

    }
}
