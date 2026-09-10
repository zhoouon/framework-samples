package com.zhoouon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2025/5/21 14:06
 * @Version: 1.0.0
 **/
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello1")
    public Mono<String> hello() {
        return Mono.just("hello");
    }

    /**
     * 每一秒响应客户端一次
     * @return
     */
    @GetMapping(value = "/hello2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> hello2() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "当前时间：" + LocalTime.now())
                .doOnNext(time -> log.info("推送时间: {}", time));
    }

    /**
     * 每1秒钟响应客户端一次
     */
    @GetMapping(value = "/realtime", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> realtimeData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "当前时间：" + LocalTime.now())
                .doOnNext(time -> log.info("推送时间: {}", time)); // 后续处理
    }

    // 每秒发送一个当前时间的字符串
    // 报头设置为 "text/event-stream"，以便于发送事件流
    @GetMapping(value = "/realtime1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> countDown() {
        //每一秒钟推送一次
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, LocalTime.now()))
                .map(data -> ServerSentEvent.<Object>builder()
                        .event("realtime1")
                        .id(Long.toString(data.getT1()))  //为每次发送设置一个id
                        .data(data.getT2().toString()).build());
    }

    @GetMapping(value = "/data-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> streamData() {
        return Flux.interval(Duration.ofSeconds(1))  // 每1秒钟发出一个递增的值
                .map(sequence -> {
                    Map<String, String> map = new HashMap<>(1);
                    map.put("aa", "bb" + sequence);
                    return map;
                });
    }
}
