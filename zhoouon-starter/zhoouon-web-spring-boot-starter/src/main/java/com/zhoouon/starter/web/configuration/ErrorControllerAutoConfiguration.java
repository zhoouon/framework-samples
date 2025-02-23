package com.zhoouon.starter.web.configuration;

import com.zhoouon.starter.web.GlobalErrorController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;

/**
 * AbstractErrorController 自动装载，替换原有的ErrorControlLer
 */
@ConditionalOnWebApplication
@AutoConfiguration(before = ErrorMvcAutoConfiguration.class)
public class ErrorControllerAutoConfiguration {

    /**
     *  注册SpringBoot默认异常处理器
     */
    @Bean
    public GlobalErrorController globalErrorController(ErrorAttributes errorAttributes) {
        return new GlobalErrorController(errorAttributes);
    }

    // @PostConstruct
    // public void customizeErrorPath() {
    // // 修改 server.error.path 的值为自定义值
    // System.setProperty("error.path", "/deprecated/error");
    // }

}
