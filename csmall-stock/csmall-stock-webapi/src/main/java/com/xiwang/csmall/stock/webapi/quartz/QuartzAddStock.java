package com.xiwang.csmall.stock.webapi.quartz;

import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.xiwang.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class QuartzAddStock implements Job {

    @Autowired
    private IStockService stockService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StockReduceCountDTO countDTO = new StockReduceCountDTO();
        countDTO.setCommodityCode("PC100");
        countDTO.setReduceCount(-10);
        stockService.reduceCommodityCount(countDTO);
        log.info("Quartz修改库存数量完成");
    }
}
