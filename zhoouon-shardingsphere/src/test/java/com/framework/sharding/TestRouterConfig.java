package com.zhoouon.sharding;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhoouon.sharding.entity.RouterConfig;
import com.zhoouon.sharding.service.RouterConfigService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-07 17:34
 * @Version: 1.0.0
 **/
@SpringBootTest
@Slf4j
public class TestRouterConfig {

    @Autowired
    private RouterConfigService routerConfigService;

    @Test
    public void save(){
        Snowflake snowflake = new Snowflake();
        for (int i = 0; i < 10; i++) {
            RouterConfig routerConfig = new RouterConfig();
            routerConfig.setRepayNo(snowflake.nextIdStr());
            routerConfig.setWifiName(RandomUtil.randomString(8));
            routerConfig.setWifiPassword(RandomUtil.randomString(10));
            routerConfig.setWifiSwitch(RandomUtil.randomInt(2));
            routerConfig.setEncryptType(RandomUtil.randomInt(2));
            routerConfig.setAdminPassword(RandomUtil.randomString(20));
            routerConfig.setHideSwitch(RandomUtil.randomInt(2));
            Integer add = routerConfigService.add(routerConfig);
            log.info("数据: {} 添加成功的状态是: {}", JSONObject.toJSONString(routerConfig), add);
        }
    }


    @Test
    public void batchSave(){
        List<RouterConfig> listRouterConfig = Lists.newArrayList();
        Snowflake snowflake = new Snowflake();
        for (int i = 0; i < 1000000; i++) {
            listRouterConfig.add(new RouterConfig(snowflake.nextIdStr(),
                    RandomUtil.randomString(8),
                    RandomUtil.randomString(10),
                    RandomUtil.randomInt(2),
                    RandomUtil.randomInt(2),
                    RandomUtil.randomString(20),
                    RandomUtil.randomInt(2)));
        }
        List<List<RouterConfig>> listPartition = ListUtils.partition(listRouterConfig, 10000);
        CountDownLatch countDownLatch = new CountDownLatch(listPartition.size());
        long beginTime = System.currentTimeMillis();
        listPartition.forEach(routerConfigs -> {
            routerConfigService.batchSave(routerConfigs,countDownLatch);
        });
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("阻塞异常: {}", e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        log.info("耗时: {}", endTime - beginTime);
    }


    @Test
    public void selectById(){
        RouterConfig routerConfig = routerConfigService.selectById(1810156736920539150L);
        log.info("分表查询出来的数据是: {}",JSONObject.toJSONString(routerConfig));

    }

    /**
     * @param
     * @return: void
     * @description: 获取CPU核心线程数
     * @author: zhoudong
     * @date: 2024-07-08 13:53
     */
    @Test
    public void getCpu(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());

    }

}
