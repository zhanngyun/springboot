package com.yun.sell.utils;

import java.util.Random;

/**
 * @Author: yzhang
 * @Date: 2018/1/25 15:34
 */
public class KeyUtil {


    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
