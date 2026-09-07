package com.zhoouon.websocket.listener;

import com.zhoouon.websocket.handler.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/// RedisMessageListener.java
@Slf4j
@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        String[] parts = payload.split(":", 2);
        if (parts.length < 2) return;

        String userId = parts[0];
        String content = parts[1];

        try {
            webSocketHandler.sendMessageToUser(userId, content);
        } catch (Exception e) {
            log.error("通过 WebSocket 推送消息失败, userId: {}", userId, e);
        }
    }
}
