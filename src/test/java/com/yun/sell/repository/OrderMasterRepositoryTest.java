package com.yun.sell.repository;

import com.yun.sell.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Test
    public void saveTest(){
        OrderMaster orderMaster =new OrderMaster("1","张三","18877770000","智汇中心","123456",new BigDecimal(15.6),0,0);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(save);

    }
    @Test
    public void findOneTest(){
        OrderMaster one = orderMasterRepository.findOne("1");
        Assert.assertNotNull(one);
    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid("123456", pageRequest);
        Assert.assertNotEquals(0,byBuyerOpenid.getContent().size());
    }



}