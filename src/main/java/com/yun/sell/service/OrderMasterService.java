package com.yun.sell.service;

import com.yun.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:22
 */
public interface OrderMasterService extends BaseService<OrderDTO,String> {

    Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /** 创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /** 订单取消*/
    OrderDTO cancel(OrderDTO orderDTO);


}
