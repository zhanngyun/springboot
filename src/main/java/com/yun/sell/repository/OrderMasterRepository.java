package com.yun.sell.repository;

import com.yun.sell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单信息
 * @Author: yzhang
 * @Date: 2018/1/24 15:41
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /** 通过用户的openid来查询订单*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
