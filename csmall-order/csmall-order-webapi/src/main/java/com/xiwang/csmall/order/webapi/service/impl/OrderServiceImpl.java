package com.xiwang.csmall.order.webapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiwang.csmall.cart.service.ICartService;
import com.xiwang.csmall.commons.exception.CoolSharkServiceException;
import com.xiwang.csmall.commons.pojo.order.dto.OrderAddDTO;
import com.xiwang.csmall.commons.pojo.order.model.Order;
import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.xiwang.csmall.commons.restful.JsonPage;
import com.xiwang.csmall.commons.restful.ResponseCode;
import com.xiwang.csmall.order.service.IOrderService;
import com.xiwang.csmall.order.webapi.mapper.OrderMapper;
import com.xiwang.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//order模块的方法会被business模块调用,所以也是生产者要加@DubboService注解
@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    //    添加@DubboReference注解,表示当前业务逻辑层中需要消费其他模块的服务
//    声明的接口应该是其他服务提供业务的业务逻辑层接口
//    因为Nacos中注册了业务的实现类,所有声明的接口会自动匹配到实现类对象
    @DubboReference
    private IStockService stockService;
    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.减少订单中商品的库存数(要调用stock模块的功能)
        StockReduceCountDTO countDTO = new StockReduceCountDTO();
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        // dubbo 调用stock减少库存的方法
        stockService.reduceCommodityCount(countDTO);
        // 2.从购物车中删除用户勾选的商品(要调用cart模块的功能)
        cartService.deleteUserCart(orderAddDTO.getUserId(), orderAddDTO.getCommodityCode());
        // 3.将orderAddDTO中的信息转换为Order实体类,然后新增到数据库中
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO, order);
        // 执行新增
        int rows = orderMapper.insertOrder(order);
        // TODO: 2022/10/26 订单失败异常
        if (rows < 1) {
            String message = "新增订单失败!";
            log.info(message);
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, message);
        }
        log.info("新增订单信息为:{}", order);
    }

    @Override
    public JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        // 上面设置好分页查询条件,下面进行的查询在执行时sql语句都会自动被追加limit关键字
        List<Order> list = orderMapper.findAllOrder();

        // list变量并不是全查结果,只是包含指定页码的数据
        return JsonPage.restPage(new PageInfo<>(list));
    }
}







