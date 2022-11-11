package com.xiwang.csmall.order.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.order.webapi.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
