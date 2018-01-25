package com.yun.sell.enums;

import lombok.Getter;

/**
 * 订单状态
 * @Author: yzhang
 * @Date: 2018/1/25 13:08
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已完结"),
    CANCEL(2,"已取消")

    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
