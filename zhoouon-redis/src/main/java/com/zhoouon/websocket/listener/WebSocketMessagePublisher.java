package com.zhoouon.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessagePublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void publishToUser(String userId, String message) {
        redisTemplate.convertAndSend("ws-channel", userId + ":" + message);
    }
}
