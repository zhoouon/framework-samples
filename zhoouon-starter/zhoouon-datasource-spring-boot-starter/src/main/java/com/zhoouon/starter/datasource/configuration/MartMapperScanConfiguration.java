
package com.zhoouon.starter.datasource.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 自定义数据模块自动配置类
 */
@SpringBootConfiguration
public class MartMapperScanConfiguration {

    /**
     * 配置统一拦截地址，这样就不再需要在启动类上指定@MapperScan注解，适用于项目中统一模块路径
     * 20230925 修复，将这个bean独立到一个单独的配置文件中，如果放在同一个配置类中此Bean会优先于配置类，这就导致配置类中读取不到Properties的值
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.zhoouon.*.mapper"); // 替换成您的 Mapper 接口所在的包名
        return scannerConfigurer;
    }

}
