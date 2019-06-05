package com.ufoai.platform.common.exception;

import com.ufoai.platform.constants.ErrorMatrix;

import com.ufoai.platform.pojo.base.ResultBean;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultBean handleBindException(
            BindException ex) {
        ResultBean result = new ResultBean();
        BindingResult bindingResult = ex.getBindingResult();
        String errorMesssage = "";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        result.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
        result.setMessage(ErrorMatrix.INVALID_PARAMTER + errorMesssage);
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultBean handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        ResultBean result = new ResultBean();
        result.setSuccess(false);
        result.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
        result.setMessage(ErrorMatrix.INVALID_PARAMTER + ErrorMatrix.INVALID_PARAMTER);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultBean handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        ResultBean result = new ResultBean();
        result.setSuccess(false);
        BindingResult bindingResult = ex.getBindingResult();
        String errorMesssage = "";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        result.setCode(ErrorMatrix.CODE_INVALID_PARAMTER);
        result.setMessage(ErrorMatrix.INVALID_PARAMTER + errorMesssage);
        return result;
    }

    @ExceptionHandler({UnknownAccountException.class,IncorrectCredentialsException.class})
    @ResponseBody
    public ResultBean shiroLoginException(
            Exception ex) {
        ResultBean result = new ResultBean();
        result.setSuccess(false);
        result.setMessage(ErrorMatrix.USER_ERROR);
        result.setCode(ErrorMatrix.CODE_LOGIN_FAILED);
        return result;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResultBean UnauthorizedException(
            UnauthorizedException ex) {
        ResultBean result = new ResultBean();
        result.setSuccess(false);
        result.setMessage(ErrorMatrix.NO_AUTH);
        result.setCode(ErrorMatrix.CODE_NO_AUTH);
        return result;
    }

    @ExceptionHandler(DisabledAccountException.class)
    @ResponseBody
    public ResultBean handlerDisabledAccountException(
            DisabledAccountException ex) {
        ResultBean result = new ResultBean();
        result.setSuccess(false);
        result.setMessage(ErrorMatrix.LOG_NO_SUCCESS);
        result.setCode(ErrorMatrix.CODE_NO_AUTH);
        return result;
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean exceptionHandler(Exception exception) {
        logger.error(exception.toString());
        ResultBean result = new ResultBean();
        result.setCode(ErrorMatrix.SYS_FAILED);
        result.setMessage(exception.getMessage()+"");
        return result;
    }


}
