package com.xiwang.csmall.order.service;

import com.xiwang.csmall.commons.pojo.order.dto.OrderAddDTO;
import com.xiwang.csmall.commons.pojo.order.model.Order;
import com.xiwang.csmall.commons.restful.JsonPage;

public interface IOrderService {

    // 声明新增订单的业务逻辑层方法
    void orderAdd(OrderAddDTO orderAddDTO);

    JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize);
}
