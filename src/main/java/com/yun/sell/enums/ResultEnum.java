package com.yun.sell.enums;

import lombok.Getter;

/**
 * 异常枚举类
 * @Author: yzhang
 * @Date: 2018/1/25 14:39
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17,"支付状态不正确"),
    ORDER_PAY_UPDATE_ERROR(18,"订单支付失败")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
