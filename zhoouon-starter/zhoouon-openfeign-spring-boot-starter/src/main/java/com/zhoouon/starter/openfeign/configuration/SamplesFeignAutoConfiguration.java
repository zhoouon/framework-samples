package com.zhoouon.starter.openfeign.configuration;

import com.zhoouon.starter.openfeign.SamplesFeignErrorDecoder;
import com.zhoouon.starter.openfeign.SamplesFeignExceptionHandler;
import com.zhoouon.starter.openfeign.SamplesFeignResponseDecoder;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * OpenFeign 自动配置类
 */
@SpringBootConfiguration
@ConditionalOnClass(Feign.class)
public class SamplesFeignAutoConfiguration {

    /**
     * 自定义Feign解码器
     */
    @Bean
    public Decoder feignDecoder() {
        return new SamplesFeignResponseDecoder();
    }

    /**
     * 自定义Feign的异常解码器
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new SamplesFeignErrorDecoder();
    }

    /**
     * Feign的全局异常转换器
     */
    @Bean
    public SamplesFeignExceptionHandler dailyMartFeignExceptionHandler() {
        return new SamplesFeignExceptionHandler();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
