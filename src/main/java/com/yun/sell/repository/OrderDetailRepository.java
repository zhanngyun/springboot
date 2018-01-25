package com.yun.sell.repository;

import com.yun.sell.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 15:48
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /** 通过orderid查询该订单下面的详细订单*/
    List<OrderDetail> findByOrderId(String orderId);
}
