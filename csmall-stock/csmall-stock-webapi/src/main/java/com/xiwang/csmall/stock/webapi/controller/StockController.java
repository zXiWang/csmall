package com.xiwang.csmall.stock.webapi.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiwang.csmall.commons.exception.CoolSharkServiceException;
import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.xiwang.csmall.commons.restful.JsonResult;
import com.xiwang.csmall.commons.restful.ResponseCode;
import com.xiwang.csmall.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Api(tags = "库存管理模块")
@RequestMapping("/base/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @PostMapping("/reduce/count")
    @ApiOperation("减少商品库存数")
    @SentinelResource(value = "减少商品库存数", blockHandler = "blockError", fallback = "fallbackError")
    public JsonResult reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        // 调用业务逻辑层
        stockService.reduceCommodityCount(stockReduceCountDTO);
        if (Math.random() < 0.5) {
            String message = "随机异常!";
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, message);
        }
        return JsonResult.ok("库存减少完成!");
    }

    //Sentinel自定义限流方法定义规则如下
//    1.访问修饰符必须为public
//    2.返回值类型必须与控制器方法一致
//    3.方法名必须是控制器方法注解中blockHandler定义的名称
//    4.方法参数必须包含控制器的所有参数,而且可以额外添加BlockException的异常类型参数
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO, BlockException e) {
        String message = "服务器忙,请稍后再试!";
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR, message);
    }

    //    降级方法:由@SentinelResource注解fallback指定的方法名
    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO, Throwable throwable) {
        String message = "运行发生异常,服务降级!";
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR, message);
    }
}

