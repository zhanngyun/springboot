package com.yun.sell.repository;

import com.yun.sell.domain.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 15:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo("1","yzhang","123","12345");
        SellerInfo save = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(save);
    }
    @Test
    public void findOneTest(){
        SellerInfo one = sellerInfoRepository.findOne("1");
        Assert.assertNotNull(one);
    }
}