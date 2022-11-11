package com.xiwang.csmall.stock.service;

import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;

public interface IStockService {

    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);
}
