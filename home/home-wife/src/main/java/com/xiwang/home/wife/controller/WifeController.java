package com.xiwang.home.wife.controller;


import com.xiwang.csmall.commons.restful.JsonResult;
import com.xiwang.home.wife.service.IWifeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
@Api(tags = "妻子活动")
@RequestMapping("/home/wife")
public class WifeController {
    @Autowired
    private IWifeService iWifeService;

    @ApiOperation("妻子开始活动")
    @GetMapping("/action")
    public JsonResult action(){
        iWifeService.action();
        String message="妻子活动完毕!";
        return JsonResult.ok(message);
    }
}
