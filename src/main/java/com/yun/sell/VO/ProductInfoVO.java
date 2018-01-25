package com.yun.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 * @Author: yzhang
 * @Date: 2018/1/25 9:20
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;


    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;


    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    public ProductInfoVO() {
    }

    public ProductInfoVO(String productId, String productName, BigDecimal productPrice, String productDescription, String productIcon) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
    }
}
