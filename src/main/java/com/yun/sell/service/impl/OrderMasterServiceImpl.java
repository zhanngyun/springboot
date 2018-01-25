package com.yun.sell.service.impl;

import com.yun.sell.domain.OrderMaster;
import com.yun.sell.repository.OrderMasterRepository;
import com.yun.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:23
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderMaster findOne(String id) {
        return orderMasterRepository.findOne(id);
    }

    @Override
    public List<OrderMaster> findAll() {
        return orderMasterRepository.findAll();
    }

    @Override
    public OrderMaster save(OrderMaster orderMaster) {
        return orderMasterRepository.save(orderMaster);
    }

    @Override
    public Page<OrderMaster> findAll(Pageable pageable) {
        return orderMasterRepository.findAll(pageable);
    }

    @Override
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        return orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
    }
}
