package com.zhoouon.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoouon.sharding.entity.RouterConfig;
import com.zhoouon.sharding.mapper.RouterConfigMapper;
import com.zhoouon.sharding.service.RouterConfigService;
import com.zhoouon.starter.common.exception.BaseException;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-07-06 14:39
 * @Version: 1.0.0
 **/
@Service
public class RouterConfigServiceImpl extends ServiceImpl<RouterConfigMapper, RouterConfig> implements RouterConfigService {

    @Autowired
    private RouterConfigMapper routerConfigMapper;

    @Autowired
    private BaseMapper baseMapper;

    @Override
    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    public Integer add(RouterConfig routerConfig) {
        return baseMapper.insert(routerConfig);
    }

    @Override
    public Integer deleteById(Long id) {
        return this.deleteById(id);
    }

    @Override
    public RouterConfig selectById(Long id) {
        LambdaQueryWrapper<RouterConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RouterConfig::getId, id);
        RouterConfig config = routerConfigMapper.selectById(queryWrapper);
        if (Objects.isNull(config)) {
            throw new BaseException("0202B052", id);
        }
        return config;
    }

    @Override
    public List<RouterConfig> selectAll() {
        return routerConfigMapper.selectList(null);
    }

    @Async("AsyncThreadPoolExecutor")
    @Override
    public void batchSave(List<RouterConfig> routerConfigs, CountDownLatch countDownLatch) {
        try {
            this.saveBatch(routerConfigs);
        }finally {
            countDownLatch.countDown();
        }
    }
}
