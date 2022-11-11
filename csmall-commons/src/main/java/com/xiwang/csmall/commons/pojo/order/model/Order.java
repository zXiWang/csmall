package com.xiwang.csmall.commons.pojo.order.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
