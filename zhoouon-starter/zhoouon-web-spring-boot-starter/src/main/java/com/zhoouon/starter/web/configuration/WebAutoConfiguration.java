package com.zhoouon.starter.web.configuration;

import com.zhoouon.starter.web.GlobalExceptionHandler;
import com.zhoouon.starter.web.GlobalResponseBodyAdvice;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Web 组件自动装配
 */
@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebAutoConfiguration {

    /**
     * 自定义全局异常处理器
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler dailyMartGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     *  接口自动包装
     */
    @Bean
    @ConditionalOnMissingBean(GlobalResponseBodyAdvice.class)
    public GlobalResponseBodyAdvice dailyMartGlobalResponseBodyAdvice() {
        return new GlobalResponseBodyAdvice();
    }

}
