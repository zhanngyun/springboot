package com.yun.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * @Author: yzhang
 * @Date: 2018/1/24 16:52
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;


    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
