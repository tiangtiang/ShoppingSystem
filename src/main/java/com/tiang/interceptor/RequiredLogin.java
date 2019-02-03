package com.tiang.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tiang
 * @date 20190203
 * 被该注解标注的方法，需要登录之后才能进行操作
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredLogin {
    UserType value() default UserType.BUYER;
}
