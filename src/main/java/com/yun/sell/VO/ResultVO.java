package com.yun.sell.VO;

import lombok.Data;

import java.util.List;

/**
 * http返回的最外层对象
 * @Author: yzhang
 * @Date: 2018/1/25 9:08
 */
@Data
public class ResultVO<T> {
    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /**返回的具体内容 */
    private List<T> data;
}
