package com.tangzhe.annotation;

import java.lang.annotation.*;

/**
 * Created by 唐哲
 * 2018-06-26 15:05
 * 想要权限拦截的接口就加上这个注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAuth {
}
