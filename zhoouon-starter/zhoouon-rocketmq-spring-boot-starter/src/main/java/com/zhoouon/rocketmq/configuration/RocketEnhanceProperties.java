package com.zhoouon.rocketmq.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zhoouon.rocketmq")
@Data
public class RocketEnhanceProperties {

    private boolean enabledIsolation;

    private String environment;
}
