package com.tiang.controller;

import com.tiang.interceptor.NotLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tiang
 * @date 20190203
 * 全局异常处理, 使用ControllerAdvice注解可以将其他controller抛出的异常截获
 */
@ControllerAdvice
public class ExecuteExecptionHandler {

    /**
     * 未登录异常
     * @return 跳转到登录页面
     */
    @ExceptionHandler(NotLoginException.class)
    public String notLoginException(){
        return "redirect:html/login.html";
    }
}
