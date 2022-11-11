package com.xiwang.home.wife.service.impl;


import com.xiwang.home.husband.service.IHusbandService;
import com.xiwang.home.wife.service.IWifeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService
@Service
public class WifeServiceImpl implements IWifeService {

    @DubboReference
    private IHusbandService iHusbandService;

    @Override
    public void action() {
        String message = iHusbandService.WashDishes();
        System.out.println(message);
    }
}
