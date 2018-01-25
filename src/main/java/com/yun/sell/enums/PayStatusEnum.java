package com.yun.sell.enums;

import lombok.Getter;

/**
 * 支付状态
 * @Author: yzhang
 * @Date: 2018/1/25 13:12
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
