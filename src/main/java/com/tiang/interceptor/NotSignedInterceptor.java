package com.tiang.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tiang
 * @date 20190201
 * 拦截器，拦截所有未登录不能访问的页面，跳转到登录界面
 */
public class NotSignedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURI();
        System.out.println(url);
        return true;
    }
}
