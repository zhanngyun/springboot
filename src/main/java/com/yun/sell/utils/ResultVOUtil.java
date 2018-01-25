package com.yun.sell.utils;

import com.yun.sell.VO.ResultVO;

import java.util.List;

/**
 * 返回结果集工具包
 * @Author: yzhang
 * @Date: 2018/1/25 11:04
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData((List) object);
        return resultVO;
    }
}
