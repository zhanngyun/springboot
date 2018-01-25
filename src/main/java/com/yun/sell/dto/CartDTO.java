package com.yun.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @Author: yzhang
 * @Date: 2018/1/25 15:52
 */
@Data
public class CartDTO {
    /** 商品ID*/
    private String productId;
    /** 商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
