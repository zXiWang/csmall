package com.xiwang.csmall.stock.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.stock.webapi.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
