package com.yun.sell.service;

import com.yun.sell.domain.OrderDetail;
import com.yun.sell.dto.OrderDTO;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/26 14:18
 */
public interface BuyerService {

    //查询一个订单
    List<OrderDetail> findOrderOne(String openid, String orderId);

    //取消订单
    void cancelOrder(String openid,String orderId);
}
