package com.yun.sell.service.impl;

import com.yun.sell.domain.SellerInfo;
import com.yun.sell.repository.SellerInfoRepository;
import com.yun.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:20
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findOne(String id) {
        return sellerInfoRepository.findOne(id);
    }

    @Override
    public List<SellerInfo> findAll() {
        return sellerInfoRepository.findAll();
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return sellerInfoRepository.save(sellerInfo);
    }

    @Override
    public Page<SellerInfo> findAll(Pageable pageable) {
        return sellerInfoRepository.findAll(pageable);
    }
}
