package com.xiwang.home.husband.service.impl;

import com.xiwang.home.husband.service.IHusbandService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService
@Service
public class HusbandServiceImpl implements IHusbandService {

    @Override
    public String WashDishes() {
        return "丈夫正在洗碗...";
    }
}
