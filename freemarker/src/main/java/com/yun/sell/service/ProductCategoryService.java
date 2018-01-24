package com.yun.sell.service;

import com.yun.sell.domain.ProductCategory;

import java.util.List;

/**
 * 类目
 * @Author: yzhang
 * @Date: 2018/1/24 14:36
 */
public interface ProductCategoryService extends BaseService<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
