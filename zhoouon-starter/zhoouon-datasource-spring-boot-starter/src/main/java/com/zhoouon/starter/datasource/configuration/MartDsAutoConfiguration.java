package com.zhoouon.starter.datasource.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zhoouon.starter.datasource.CustomIdGenerator;
import com.zhoouon.starter.datasource.handler.MartMetaObjectHandler;
import com.zhoouon.starter.datasource.intecept.SlowSqlInterceptor;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 * 自定义数据模块自动配置类
 */
@SpringBootConfiguration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@EnableConfigurationProperties(SlowSQLProperties.class)
public class MartDsAutoConfiguration {

    @Resource
    private SlowSQLProperties slowSQLProperties;

    /**
     * 设置mybatis-plus拦截器
     * 1. 分页拦截器
     * 2. 乐观锁拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * 在 插入/更新 数据时自动填充默认值
     */
    @Bean
    public MartMetaObjectHandler myMetaObjectHandler() {
        return new MartMetaObjectHandler();
    }

    /**
     * 注入mybatis插件，可以统计SQL执行耗时
     */
    @Bean
    @ConditionalOnProperty(name = "zhoouon.sql.slow.enable", havingValue = "true", matchIfMissing = true)
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.addInterceptor(new SlowSqlInterceptor(slowSQLProperties));
    }

    /**
     * 配置统一拦截地址，这样就不再需要在启动类上指定@MapperScan注解了
     * 适用于项目中统一模块路径
     */
    // @Bean
    // public MapperScannerConfigurer mapperScannerConfigurer() {
    // MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
    // scannerConfigurer.setBasePackage("com.jianzh5.dailymart.module.*.infrastructure.dao.mapper"); // 替换成您的 Mapper 接口所在的包名
    // return scannerConfigurer;
    // }

    /**
     * 替换Mybatis-plus的算法生成器
     */
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new CustomIdGenerator();
    }

}
