package com.zhoouon.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2026-09-07 21:05
 * @Version: 1.0.0
 **/
@SpringBootTest(classes = {AiApplication.class})
public class TestOllama {

    /**
     * 同步阻塞的方式进行输出
     * @param chatModel
     */
    @Test
    public void testOllama1(@Autowired OllamaChatModel chatModel) {
        String call = chatModel.call("你好，你是谁");
        System.out.println(call);
    }

    /**
     * 通过流的方式输出
     * @param chatModel
     */
    @Test
    public void testOllama2(@Autowired OllamaChatModel chatModel) {
        Flux<String> stream = chatModel.stream("你好，你是谁");
        stream.toIterable().forEach(System.out::println);
    }

    /**
     * 通过温度的方式对输出内容进行人性化设置
     * @param chatModel
     */
    @Test
    public void testOllama3(@Autowired OllamaChatModel chatModel) {
    }

    /**
     * 同步阻塞 深度思考
     * @param chatModel
     */
    @Test
    public void testOllama4(@Autowired OllamaChatModel chatModel) {
    }

    /**
     * 通过流的方式将深度思考的结果输出
     * @param chatModel
     */
    @Test
    public void testOllama5(@Autowired OllamaChatModel chatModel) {
    }
}
