package com.yun.sell.service.impl;

import com.yun.sell.domain.ProductInfo;
import com.yun.sell.repository.ProductInfoRepository;
import com.yun.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:16
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return productInfoRepository.findByProductStatus(productStatus);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }
}
