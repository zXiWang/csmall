package com.xiwang.csmall.cart.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.cart.webapi.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
