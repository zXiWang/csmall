package com.xiwang.csmall.stock.webapi.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 这个触发实际上是由Spring管理的Scheduler对下调度的
@Slf4j
@Configuration
public class QuartzConfig {

    int i = 1;

    @Bean
    public JobDetail showTime() {
        log.info(this.getClass().getName() + "running......");
        return JobBuilder.newJob(QuartzJob.class)
                // 需要为当前任务起个名字,方便Quartz调用
                .withIdentity("dateTime")
                // 默认情况下,JobDetail对象生成后,如果没有触发器绑定会被立即移除
                // 设置storeDurably方法后,当前JobDetail对象生成后,不会被移除了
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail addCount() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "running......");
        return JobBuilder.newJob(AddCountJob.class)
                // 需要为当前任务起个名字,方便Quartz调用1
                .withIdentity("addCount")
                // 默认情况下,JobDetail对象生成后,如果没有触发器绑定会被立即移除
                // 设置storeDurably方法后,当前JobDetail对象生成后,不会被移除了
                .storeDurably()
                .build();
    }

    // 指定任务运行时机
    @Bean
    public Trigger showTimeTrigger() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "running......");
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0/15 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(showTime())
                .withIdentity("dateTrigger")
                .withSchedule(cron)
                .build();
    }

    // 指定任务运行时机
    @Bean
    public Trigger addCountTrigger() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "running......");
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0 */2 * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(addCount())
                .withIdentity("addCountTrigger")
                .withSchedule(cron)
                .build();
    }

}
