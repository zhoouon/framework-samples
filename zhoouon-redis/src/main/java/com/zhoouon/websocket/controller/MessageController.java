package com.zhoouon.websocket.controller;

import com.zhoouon.websocket.listener.WebSocketMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private WebSocketMessagePublisher publisher;

    @PostMapping("/send")
    public ResponseEntity<String> sendMsg(@RequestParam String userId, @RequestParam String msg) {
        publisher.publishToUser(userId, msg);
        return ResponseEntity.ok("Sent to Redis");
    }
}
