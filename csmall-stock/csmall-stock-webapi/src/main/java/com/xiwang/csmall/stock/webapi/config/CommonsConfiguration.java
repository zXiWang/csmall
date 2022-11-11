package com.xiwang.csmall.stock.webapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 因为我们要套用commons项目中的异常相关支持,所以要讲异常包扫描,讲对象保存到当前项目的spring容器中
@Configuration  // 当前类是一个Spring配置类
@ComponentScan("com.xiwang.csmall.commons.exception")
public class CommonsConfiguration {
}
