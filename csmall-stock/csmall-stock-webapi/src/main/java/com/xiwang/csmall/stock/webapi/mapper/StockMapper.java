package com.xiwang.csmall.stock.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {

    @Update("update stock_tbl set count=count-#{reduceCount}" +
            " where commodity_code=#{commodityCode} and count>=#{reduceCount}")
    int updateStockCount(@Param("commodityCode") String commodityCode,
                         @Param("reduceCount") Integer reduceCount);

    @Update("update stock_tbl set count=count+10 " +
            "where commodity_code='PC100'")
    int addCount();
}
