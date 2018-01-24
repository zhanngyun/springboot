package com.yun.sell.repository;

import com.yun.sell.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 13:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Test
    public void findObeTest(){
        ProductCategory one = productCategoryRepository.findOne(1);
        System.out.println(one.toString());
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("肉类");
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateTest(){
        ProductCategory one = productCategoryRepository.findOne(1);
        one.setCategoryType(10);
        productCategoryRepository.save(one);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
        byCategoryTypeIn.forEach(e->{
            System.out.println(e.toString());
        });

    }

}