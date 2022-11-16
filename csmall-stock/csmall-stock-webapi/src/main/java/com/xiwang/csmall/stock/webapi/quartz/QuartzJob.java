package com.xiwang.csmall.stock.webapi.quartz;


import com.xiwang.csmall.stock.webapi.quartz.utils.RedisBloomUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class QuartzJob implements Job {

    static int i = 1;
    // 向RabbitMQ发送消息
    // 也是从Spring容器中获取SpringBoot启动时,创建好的操作RabbitMQ的对象
    @Autowired
    private RabbitTemplate rabbitTemplate;
    // 装配redis中操作布隆过滤器的对象
    @Autowired
    private RedisBloomUtils redisBloomUtils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 一个简单的任务演示
        // 输出当前系统时间,使用sout或log皆可
        log.info("---------------" + LocalDateTime.now() + "-----------------" + i);

        // 先定义要保存的布隆过滤器的元素数组
        String[] colors = {"red", "origin", "yellow", "green", "blue", "pink", "white"};
        // 定义布隆过滤器保存在Redis中的key
        final String COLOR_BLOOM = "color_bloom";
        // 布隆过滤器默认100个元素误判率1%
        redisBloomUtils.bfmadd(COLOR_BLOOM, colors);
        // 定义一个要判断的元素
        String el = "blue";
        // 判断是否在布隆过滤器中
        System.out.println(el + "是否在定义的数组中:" +
                redisBloomUtils.bfexists(COLOR_BLOOM, el));


//        // 实例化Stock对象
//        Stock stock=new Stock();
//        stock.setId(i++);
//        stock.setCommodityCode("PC100");
//        stock.setReduceCount(RandomUtils.nextInt(20)+1);
//        // 利用RabbitTemplate发送消息
//        //convertAndSend([交换机名称],[路由key名称],[要发送的对象])
//        rabbitTemplate.convertAndSend(
//                RabbitMQConfig.STOCK_EX,
//                RabbitMQConfig.STOCK_ROUT,
//                stock);
//        log.info("发送消息完成:{}",stock);


    }
}
