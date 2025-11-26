package com.zhoouon.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/4/2 15:15
 * @Version: 1.0.0
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.zhoouon.sharding.mapper"})
@EnableAspectJAutoProxy(exposeProxy = true)
@RefreshScope
@EnableDiscoveryClient
public class ShardingSphereApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class);
    }
}
