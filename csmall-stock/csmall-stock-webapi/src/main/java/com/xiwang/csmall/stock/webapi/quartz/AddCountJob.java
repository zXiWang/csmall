package com.xiwang.csmall.stock.webapi.quartz;

import com.xiwang.csmall.stock.webapi.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j

public class AddCountJob implements Job {
    @Autowired
    private StockMapper stockMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("--------------" + LocalDateTime.now() + "--------------");
        stockMapper.addCount();
    }

}
