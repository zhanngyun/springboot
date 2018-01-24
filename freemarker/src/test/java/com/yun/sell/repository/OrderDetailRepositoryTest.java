package com.yun.sell.repository;

import com.yun.sell.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail("1","1","1","花菜",new BigDecimal(15),1,"img/icon.jpg");
        OrderDetail save = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(save);
    }
    @Test
    public void findOneTest(){
        OrderDetail one = orderDetailRepository.findOne("1");
        Assert.assertNotNull(one);
    }

}