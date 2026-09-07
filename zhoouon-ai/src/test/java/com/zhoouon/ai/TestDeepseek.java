package com.zhoouon.ai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2026-09-07 21:05
 * @Version: 1.0.0
 **/
@SpringBootTest(classes = {AiApplication.class})
public class TestDeepseek {

    @Test
    public void testDeepseek(DeepSeekChatModel chatModel) {
        String call = chatModel.call("你好，你是谁");
        System.out.println(call);
    }
}
