package com.zhoouon.rocketmq.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/11/27 21:37
 */
@ConfigurationProperties(prefix = "dailymart.rocketmq")
@Data
public class RocketEnhanceProperties {

    private boolean enabledIsolation;

    private String environment;
}
