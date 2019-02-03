package com.tiang.interceptor;

import com.tiang.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpSession;

/**
 * @author tiang
 * @date 20190203
 * 对RequireLogin注解标注的方法进行切面
 */
@Aspect
public class LoginAspect {

    /**
     * 定义切点
     * @param session 切点有一个必要参数，session
     */
    @Pointcut("execution(* com.tiang.controller..*.*(..))&&@annotation(RequiredLogin)&&args(..,session)")
    private void method(HttpSession session){

    }

    /**
     * 对该方法的执行进行环绕处理，如果没有登录，或者登录类型不匹配就抛出异常
     * @param point 被切方法
     * @param session 用来获取用户信息
     * @param login 注解
     * @return 正常处理结果
     * @throws Throwable 可能抛出的异常
     */
    @Around(value = "method(session) && @annotation(login)", argNames = "point, session, login")
    public Object executeMethod(ProceedingJoinPoint point, HttpSession session, RequiredLogin login) throws Throwable {
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            if((login.value() == UserType.BUYER && user.getIsBuyer() == 1)
                ||(login.value() == UserType.SELLER && user.getIsBuyer() == 0)){
                return point.proceed();
            }
        }
        throw new NotLoginException();
    }
}
