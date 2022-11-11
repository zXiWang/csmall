package com.xiwang.csmall.commons.pojo.cart.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable {
    private Integer id;
    private String commodityCode;
    private Integer price;
    private Integer count;
    private String userId;

}
