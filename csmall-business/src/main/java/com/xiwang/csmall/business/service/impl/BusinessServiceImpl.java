package com.xiwang.csmall.business.service.impl;

import com.xiwang.csmall.business.service.IBusinessService;
import com.xiwang.csmall.commons.pojo.order.dto.OrderAddDTO;
import com.xiwang.csmall.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {
//    Dubbo调用order模块得到新增订单功能
//    当前business是单纯的消费者,不需要在类上编写@DubboService

    @DubboReference
    private IOrderService dubboOrderService;

    @GlobalTransactional
    @Override
    public void buy() {
        // 模拟购买业务
        // 实例化一个用于新增订单的DTO对象
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        // 为属性赋值
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(3);
        orderAddDTO.setMoney(500);
        // 因为是模拟购买,还没有操作数据库的条件,所以要输出即可
        log.info("新增订单信息为:{}", orderAddDTO);
        //dubbo调用,将上面的实例化的订单信息生成为订单,影响数据库信息
        dubboOrderService.orderAdd(orderAddDTO);
    }
}
