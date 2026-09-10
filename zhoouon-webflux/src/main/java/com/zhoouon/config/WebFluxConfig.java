package com.zhoouon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2025/5/20 14:57
 * @Version: 1.0.0
 **/
@Configuration
public class WebFluxConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"),
                request -> ServerResponse.ok().bodyValue("Hello,World!")
        );
    }
}
