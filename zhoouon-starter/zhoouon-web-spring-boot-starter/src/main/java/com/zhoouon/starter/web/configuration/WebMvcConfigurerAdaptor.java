package com.zhoouon.starter.web.configuration;

import com.zhoouon.starter.web.interceptor.RequestHeaderInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebMvcConfigurerAdaptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/api/pd/**")
                .excludePathPatterns(excludePathList);

    }

    private final String[] excludePathList = new String[]{
            "/api/pd/customer/login"
    };

    @Bean
    public HandlerInterceptor userInterceptor() {
        return new RequestHeaderInterceptor();
    }
}
