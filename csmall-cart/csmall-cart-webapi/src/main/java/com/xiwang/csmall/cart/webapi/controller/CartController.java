package com.xiwang.csmall.cart.webapi.controller;

import com.xiwang.csmall.cart.service.ICartService;
import com.xiwang.csmall.commons.pojo.cart.dto.CartAddDTO;
import com.xiwang.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/base/cart")
@Api(tags = "购物车管理模块")
public class CartController {
    // 控制层中装配业务逻辑层对象
    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    @ApiOperation("新增购物车中商品")
    public JsonResult cartAdd(CartAddDTO cartAddDTO){
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok("新增购物车商品完成!");
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车中商品")
    @ApiImplicitParams({
      @ApiImplicitParam(value = "用户ID",name="userId",example = "UU100"),
      @ApiImplicitParam(value = "商品编号",name="commodityCode",example = "PC100")
    })
    public JsonResult deleteUserCart(String userId,String commodityCode){
        cartService.deleteUserCart(userId,commodityCode);
        return JsonResult.ok("删除购物车完成!");
    }


}



