package com.yun.sell.service.impl;

import com.yun.sell.domain.OrderDetail;
import com.yun.sell.dto.OrderDTO;
import com.yun.sell.enums.ResultEnum;
import com.yun.sell.exception.SellException;
import com.yun.sell.service.BuyerService;
import com.yun.sell.service.OrderDetailService;
import com.yun.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/26 14:21
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;


    @Autowired
    private OrderDetailService orderDetailService;


    @Override
    public List<OrderDetail> findOrderOne(String openid, String orderId) {
        checkOrderOwner(openid,orderId);
        List<OrderDetail> byOrderId = orderDetailService.findByOrderId(orderId);
        return byOrderId;
    }

    @Override
    public void cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO==null){
            log.error("【取消订单】查询不到带订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMasterService.cancel(orderDTO);
    }


    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单订单的openid不一致.openid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

}
