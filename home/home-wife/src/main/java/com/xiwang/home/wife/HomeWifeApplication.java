package com.xiwang.home.wife;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class HomeWifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeWifeApplication.class, args);
    }

}
