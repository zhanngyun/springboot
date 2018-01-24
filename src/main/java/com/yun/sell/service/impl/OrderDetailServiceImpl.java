package com.yun.sell.service.impl;

import com.yun.sell.domain.OrderDetail;
import com.yun.sell.repository.OrderDetailRepository;
import com.yun.sell.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:25
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public OrderDetail findOne(String id) {
        return orderDetailRepository.findOne(id);
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public Page<OrderDetail> findAll(org.springframework.data.domain.Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }
}
