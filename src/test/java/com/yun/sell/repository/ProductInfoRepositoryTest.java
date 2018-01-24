package com.yun.sell.repository;

import com.yun.sell.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo("2","菠菜",new BigDecimal(6),20,"绿色食品","img/icon.jpg",0,2);
        ProductInfo save = productInfoRepository.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void selectOne(){
        List<ProductInfo> byCategoryTypeIn = productInfoRepository.findByCategoryTypeIn(Arrays.asList(2));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,byProductStatus.size());
    }
}