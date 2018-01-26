package com.yun.sell.service.impl;

import com.yun.sell.domain.OrderDetail;
import com.yun.sell.dto.OrderDTO;
import com.yun.sell.enums.OrderStatusEnum;
import com.yun.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: yzhang
 * @Date: 2018/1/25 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    private final String OPEN_ID = "122342526";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("sunCloud");
        orderDTO.setBuyerAddress("杭州");
        orderDTO.setBuyerOpenid(OPEN_ID);
        orderDTO.setBuyerPhone("15539730302");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(10);
        orderDetailList.add(orderDetail);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("3");
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail1);
        orderDTO.setOrderDetailList(orderDetailList);
        orderMasterService.create(orderDTO);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderMasterService.findOne("1516870723773152632");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1516870484588583501");
        OrderDTO cancel = orderMasterService.cancel(orderDTO);
        Assert.assertNotNull(cancel);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1516870830836513039");
        OrderDTO cancel = orderMasterService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void paid(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1516870269042535771");
        OrderDTO cancel = orderMasterService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),cancel.getPayStatus());
    }
    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderDTO> byBuyerOpenid = orderMasterService.findByBuyerOpenid("122342526", pageRequest);
        Assert.assertNotEquals(0,byBuyerOpenid.getContent().size());
    }
}