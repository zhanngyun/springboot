package com.yun.sell.service;

import com.yun.sell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:22
 */
public interface OrderMasterService extends BaseService<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
