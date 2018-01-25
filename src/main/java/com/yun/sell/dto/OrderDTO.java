package com.yun.sell.dto;

import com.yun.sell.domain.OrderDetail;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/25 13:44
 */
@Data
public class OrderDTO {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     *买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态 默认为新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态 默认为未支付
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
