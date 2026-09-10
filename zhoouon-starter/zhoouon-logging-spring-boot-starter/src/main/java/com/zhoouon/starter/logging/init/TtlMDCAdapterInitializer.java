package com.zhoouon.starter.logging.init;

import org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: zhoudong
 * @Description: 初始化TtlMDCAdapter实例，并替换MDC中的adapter对象
 * @Date: 2024/4/9 14:37
 * @Version: 1.0.0
 **/
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //加载TtlMDCAdapter实例
        TtlMDCAdapter.getInstance();
    }
}
