package com.xiwang.csmall.stock.webapi.quartz;

import com.xiwang.csmall.commons.pojo.stock.model.Stock;
import com.xiwang.csmall.stock.webapi.quartz.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 当前接收消息的类,也要保存到Spring容器中
@Component
// 和Kafka不同,RabbitMQ监听器的注解要写在类上
@RabbitListener(queues = RabbitMQConfig.STOCK_QUEUE)
@Slf4j
public class RabbitMQConsumer {

    // 在类上标记的监听器注解,不能明确接收到消息时运行哪个方法
    // 所以我们要在类中定义一个专门处理消息的方法,并使用@RabbitHandler注解标记
    // 每个类只允许一个方法标记这个注解
    // 方法参数比Kafka更简单,直接声明要接收信息的对象即可
    @RabbitHandler
    public void process(Stock stock) {
        log.info("消息接收完成!!!:{}", stock);
    }


}
