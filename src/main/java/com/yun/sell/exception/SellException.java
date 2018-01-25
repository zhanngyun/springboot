package com.yun.sell.exception;

import com.yun.sell.enums.ResultEnum;

/**
 * 自定义异常 SellException
 * @Author: yzhang
 * @Date: 2018/1/25 14:42
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }







}
