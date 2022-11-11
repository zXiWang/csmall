package com.xiwang.csmall.cart.webapi.service.impl;

import com.xiwang.csmall.cart.service.ICartService;
import com.xiwang.csmall.cart.webapi.mapper.CartMapper;
import com.xiwang.csmall.commons.pojo.cart.dto.CartAddDTO;
import com.xiwang.csmall.commons.pojo.cart.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
@Service
@Slf4j
public class CartServiceImpl implements ICartService {
    // 装配CartMapper对象
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        // 方法参数CartAddDTO,但是要执行mapper中给定的新增方法需要实体类Cart
        // 所以我们要将cartAddDTO对象中的属性值赋值为Cart实体类对象
        // 先实例化一个Cart实体类对象
        Cart cart = new Cart();
        // 使用BeanUtils类中给定的方法,将同名属性赋值
        BeanUtils.copyProperties(cartAddDTO, cart);
        // cart被赋值之后,就具备了调用mapper实现新增的条件了
        cartMapper.insertCart(cart);
        // 运行完成,输出日志信息
        log.info("新增购物车商品完成!:{}", cart);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        // 删除方法的参数是直接可以使用的,无需转换
        int rows = cartMapper.deleteCartByUserIdAndCommodityCode(userId, commodityCode);
        // 日志输出信息
        log.info("购物车删除完成");
    }
}
