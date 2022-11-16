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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
@RequestMapping("/base/cart")
@Api(tags = "购物车管理模块")
public class CartController {
    // 控制层中装配业务逻辑层对象
    @Autowired
    private ICartService cartService;
    // 装配能执行RestTemplate方法调用的对象
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
    @ApiOperation("新增购物车中商品")
    public JsonResult cartAdd(CartAddDTO cartAddDTO) {
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok("新增购物车商品完成!");
    }

    @GetMapping("/delete")
    @ApiOperation("删除购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID", name = "userId", example = "UU100"),
            @ApiImplicitParam(value = "商品编号", name = "commodityCode", example = "PC100")
    })
    public JsonResult deleteUserCart(String userId, String commodityCode) {
        cartService.deleteUserCart(userId, commodityCode);
        // RestTemplate调用减少库存的方法
        // 我们设计删除购物车后减少库存,要调用RestTemplate首先确定url
        String url = "http://localhost:20003/base/stock/reduce/count?" +
                "commodityCode={1}&reduceCount={2}";
        // 执行发起调用
        // getForObject方法参数和返回值的解释
        // 返回值:根据调用的控制器方法的实际返回值给定返回类型即可
        // 参数分为3部分
        // 1.第一个参数:就是请求的url,指定路径即可
        // 2.第二个参数:就是返回值类型的反射,根据要求编写在参数位置即可
        // 3.从第三个参数开始:往后的每个参数都是在给url路径中的{x}占位符赋值
        //   第三个参数赋值给{1} 第四个参数赋值给{2},....以此类推
        JsonResult jsonResult = restTemplate.getForObject(
                url, JsonResult.class, commodityCode, 5);
        System.out.println(jsonResult);
        return JsonResult.ok("删除购物车完成!");
    }


}



