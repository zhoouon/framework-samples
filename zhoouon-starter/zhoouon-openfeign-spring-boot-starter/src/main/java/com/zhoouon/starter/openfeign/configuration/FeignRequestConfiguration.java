package com.zhoouon.starter.openfeign.configuration;

import com.zhoouon.starter.web.RequestHeaderHolder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Feign调用时传递请求头
 */
@Slf4j
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            Map<String, String> headerMap = RequestHeaderHolder.getInstance().get();
            if (headerMap != null) {
                headerMap.forEach((key, value) -> {
                    if (log.isDebugEnabled()) {
                        log.debug("--从ThreadLocal获取消息头传递到下一个服务：key-[{}],value-[{}]", key, value);
                    }
                    template.header(key, value);
                });
            }
        };
    }

}
