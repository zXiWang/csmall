package com.xiwang.csmall.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiwang.csmall.business.service.IBusinessService;
import com.xiwang.csmall.commons.exception.CoolSharkServiceException;
import com.xiwang.csmall.commons.restful.JsonResult;
import com.xiwang.csmall.commons.restful.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/base/business")
@Api(tags = "业务触发模块")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;

    // localhost:20000/base/business/buy
    // 因为代码设定的请求方式是Post,所以不能使用浏览器输入地址栏的方式测试,必须用knife4j
    @PostMapping("/buy")
    @ApiOperation("执行业务的触发")
    @SentinelResource(value = "购买商品", blockHandler = "blockError", fallback = "fallbackError")
    public JsonResult buy() {
        // 调用业务逻辑层方法
        businessService.buy();
        if (Math.random() < 0.5) {
            String message = "随机异常!";
            throw new CoolSharkServiceException(ResponseCode.INTERNAL_SERVER_ERROR, message);
        }
        return JsonResult.ok("购买完成!");
    }

    public JsonResult blockError(BlockException e) {
        String message = "服务器忙,请稍后再试!";
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR, message);

    }

    public JsonResult fallbackError(Throwable throwable) {
        String message = "运行发生异常,服务降级!";
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR, message);
    }


}
