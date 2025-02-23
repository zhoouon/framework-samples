package com.zhoouon.sharding.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhoudong
 * @Description: 使用springboot线程池批量保存
 * @Date: 2024-07-08 10:48
 * @Version: 1.0.0
 **/
@Component
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor AsyncThreadPoolExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数一般为 cpu * 2 + 1
        threadPoolTaskExecutor.setCorePoolSize(33);
        // 最大线程数一般为 核心线程数 * 2
        threadPoolTaskExecutor.setMaxPoolSize(66);
        threadPoolTaskExecutor.setQueueCapacity(120);
        threadPoolTaskExecutor.setKeepAliveSeconds(30);
        threadPoolTaskExecutor.setThreadNamePrefix("batchSave_");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
