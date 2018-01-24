package com.yun.sell.service.impl;

import com.yun.sell.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryImpl;


    @Test
    public void findOne() {
        ProductCategory one = productCategoryImpl.findOne(2);
        Assert.assertEquals(new Integer(2),one.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = productCategoryImpl.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = productCategoryImpl.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory(new Integer(4),"有机蔬菜",1);
        ProductCategory save = productCategoryImpl.save(productCategory);
        Assert.assertNotNull(save);
    }
}