package com.boot.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @PackageName: com.boot.jwt.config
 * @ClassName: UserToken
 * @Description: This is UserToken class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserToken {
    boolean required() default true;
}
