package com.xiwang.csmall.cart.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDubbo
@SpringBootApplication
public class CsmallCartWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallCartWebapiApplication.class, args);
    }

    @Bean
    // 启用负载均衡, 因为Dubbo自带负载均衡,但是RestTemplate是代替Dubbo的,所以要单独设置
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
