package com.zhoouon.websocket.config;

import com.zhoouon.websocket.listener.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/// RedisListenerConfig.java
@Configuration
public class RedisListenerConfig {

    @Autowired
    private RedisMessageListener redisMessageListener;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(redisMessageListener, new ChannelTopic("ws-channel"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisMessageListener redisMessageListener) {
        return new MessageListenerAdapter(redisMessageListener, "listenerAdapter");
    }
}
