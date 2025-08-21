package com.zhoouon.starter.monitor.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Monitor {
    String logType() default "BUSINESS";
    long timeoutMs() default -1;
    boolean alertOnException() default true;
    String[] tags() default {};
}
