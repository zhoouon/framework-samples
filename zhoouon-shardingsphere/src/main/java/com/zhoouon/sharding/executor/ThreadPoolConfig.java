package com.zhoouon.sharding.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhoudong
 * @Description: 使用springboot线程池批量保存
 * @Date: 2024-07-08 10:48
 * @Version: 1.0.0
 **/
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolExecutor() {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数一般为 cpu * 2 + 1
        threadPoolTaskExecutor.setCorePoolSize(cpuCount * 2 + 1);
        // 最大线程数一般为 核心线程数 * 2
        threadPoolTaskExecutor.setMaxPoolSize(cpuCount * 4 + 2);
        threadPoolTaskExecutor.setQueueCapacity(120);
        threadPoolTaskExecutor.setKeepAliveSeconds(30);
        threadPoolTaskExecutor.setThreadNamePrefix("batchSave_");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // ThreadPoolTaskExecutor 作为 Spring Bean 时由容器自动执行 initialize()
        return threadPoolTaskExecutor;
    }
}
