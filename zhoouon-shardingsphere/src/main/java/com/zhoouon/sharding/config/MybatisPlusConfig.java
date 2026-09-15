package com.zhoouon.sharding.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/4/4 12:13
 * @Version: 1.0.0
 **/
@Configuration
public class MybatisPlusConfig {
    /**
     * 新的分页插件,一缓和二缓遵循 mybatis 的规则
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 分页设置
     * @return  分页拦截器
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        // 单页分页条数限制
        innerInterceptor.setMaxLimit(100L);
        // 数据库类型
        innerInterceptor.setDbType(DbType.MYSQL);
        // 当超过最大页数时不会报错
        innerInterceptor.setOverflow(true);
        return innerInterceptor;
    }
}
