package com.yun.sell.converter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.yun.sell.domain.OrderDetail;
import com.yun.sell.dto.OrderDTO;
import com.yun.sell.enums.ResultEnum;
import com.yun.sell.exception.SellException;
import com.yun.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换
 * @Author: yzhang
 * @Date: 2018/1/26 10:40
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){


        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = (List<OrderDetail>)JSONObject.parseArray(orderForm.getItems(),OrderDetail.class);
        }catch (Exception e){
            log.error("【对象转换】错误,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


}
