package com.zhoouon.starter.common.action;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-10 11:13
 * @Version: 1.0.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Function {
    String value() default "";

    String descreption() default "";

    boolean async() default false;
}
