package com.zhoouon.sharding.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource shardingSphereDataSource) {
        return new DataSourceProxy(shardingSphereDataSource);
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceProxy);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml")
        );
        return factoryBean.getObject();
    }
}
