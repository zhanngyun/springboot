package com.yun.sell.service;

import com.yun.sell.domain.OrderDetail;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:25
 */
public interface OrderDetailService extends BaseService<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);
}
