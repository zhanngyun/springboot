package com.yun.sell.service;

import com.yun.sell.domain.ProductInfo;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:11
 */
public interface ProductInfoService extends BaseService<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

    /** 查询所有的上架商品*/
    List<ProductInfo> findProductUp();

    /** 查询所有的下架的商品*/
    List<ProductInfo> findProductDown();

    //加库存


    //减库存
}
