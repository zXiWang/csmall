package com.xiwang.csmall.stock.webapi.service.impl;

import com.xiwang.csmall.commons.exception.CoolSharkServiceException;
import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.xiwang.csmall.commons.restful.ResponseCode;
import com.xiwang.csmall.stock.service.IStockService;
import com.xiwang.csmall.stock.webapi.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@DubboService注解,标记的业务逻辑层实现类,其中所有的方法都会注册于Nacos
@DubboService
@Slf4j
@Service
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;

    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        int rows = stockMapper.updateStockCount(
                stockReduceCountDTO.getCommodityCode(),
                stockReduceCountDTO.getReduceCount()
        );
        if (rows < 1) {
            String message = "库存减少失败!";
            log.info(message);
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, message);
        }
        log.info("库存减少成功!");

    }
}
