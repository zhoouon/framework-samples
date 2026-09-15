package com.zhoouon.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekAssistantMessage;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Objects;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2026-09-07 21:05
 * @Version: 1.0.0
 **/
@SpringBootTest(classes = {AiApplication.class})
public class TestDeepseek {

    /**
     * 同步阻塞的方式进行输出
     * @param chatModel
     */
    @Test
    public void testDeepseek1(@Autowired DeepSeekChatModel chatModel) {
        String call = chatModel.call("你好，你是谁");
        System.out.println(call);
    }

    /**
     * 通过流的方式输出
     * @param chatModel
     */
    @Test
    public void testDeepseek2(@Autowired DeepSeekChatModel chatModel) {
        Flux<String> stream = chatModel.stream("你好，你是谁");
        stream.toIterable().forEach(System.out::println);
    }

    /**
     * 通过温度的方式对输出内容进行人性化设置
     * @param chatModel
     */
    @Test
    public void testDeepseek3(@Autowired DeepSeekChatModel chatModel) {
        DeepSeekChatOptions opetion = DeepSeekChatOptions.builder()
                .temperature(0.1)
                .model("deepseek-chat")
                .build();
        Prompt prompt = new Prompt("请写一句诗描述清晨", opetion);
        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getText());
    }

    /**
     * 同步阻塞 深度思考
     * @param chatModel
     */
    @Test
    public void testDeepseek4(@Autowired DeepSeekChatModel chatModel) {
        Prompt prompt = new Prompt("你好 你是谁");
        ChatResponse chatResponse = chatModel.call(prompt);
        DeepSeekAssistantMessage assistantMessage = (DeepSeekAssistantMessage)chatResponse.getResult().getOutput();
        System.out.println(assistantMessage.getReasoningContent());
        System.out.println("-----------------------------------------------------");
        System.out.println(assistantMessage.getText());
    }

    /**
     * 通过流的方式将深度思考的结果输出
     * @param chatModel
     */
    @Test
    public void testDeepseek5(@Autowired DeepSeekChatModel chatModel) {
        Prompt prompt = new Prompt("你好 你是谁");
        Flux<ChatResponse> stream = chatModel.stream(prompt);
        stream.toIterable().forEach(chatResponse -> {
            DeepSeekAssistantMessage deepSeekAssistantMessage = (DeepSeekAssistantMessage) chatResponse.getResult().getOutput();
            if (Objects.isNull(deepSeekAssistantMessage)) {
                return;
            }
            System.out.print(deepSeekAssistantMessage.getText());
        });
    }
}
