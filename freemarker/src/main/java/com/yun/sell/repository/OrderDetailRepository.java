package com.yun.sell.repository;

import com.yun.sell.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 15:48
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
