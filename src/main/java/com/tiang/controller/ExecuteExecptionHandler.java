package com.tiang.controller;

import com.tiang.interceptor.NotLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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
    public String notLoginException(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if(header!=null && header.equals("XMLHttpRequest")){
            return "needLogin";
        }else {
            return "redirect:/html/login.html";
        }
    }

    /**
     * 当发生其他无法处理的异常时，跳转到错误界面
     * @return 错误界面地址
     */
    @ExceptionHandler(Exception.class)
    public String globalException(Exception ex){
        System.out.println(ex.getMessage());
        return "redirect:/html/500-error.html";
    }
}
