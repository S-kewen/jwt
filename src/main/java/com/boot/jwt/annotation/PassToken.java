package com.boot.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PackageName: com.boot.jwt.config
 * @ClassName: PassToken
 * @Description: This is PassToken class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
