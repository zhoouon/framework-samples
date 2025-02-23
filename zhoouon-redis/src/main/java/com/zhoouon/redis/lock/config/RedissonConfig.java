package com.zhoouon.redis.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson bean管理
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(clusterNodes.split(","));
        return Redisson.create(config);
    }
}
