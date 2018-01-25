package com.yun.sell.service.impl;

import com.yun.sell.domain.OrderDetail;
import com.yun.sell.domain.OrderMaster;
import com.yun.sell.domain.ProductInfo;
import com.yun.sell.dto.CartDTO;
import com.yun.sell.dto.OrderDTO;
import com.yun.sell.enums.OrderStatusEnum;
import com.yun.sell.enums.PayStatusEnum;
import com.yun.sell.enums.ResultEnum;
import com.yun.sell.exception.SellException;
import com.yun.sell.repository.OrderDetailRepository;
import com.yun.sell.repository.OrderMasterRepository;
import com.yun.sell.service.OrderMasterService;
import com.yun.sell.service.ProductInfoService;
import com.yun.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:23
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        //总价
        BigDecimal orderAmount=  new BigDecimal(BigInteger.ZERO);

        //orderID
        String orderId = KeyUtil.getUniqueKey();

        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            //1.查询商品（数量价格）
            //通过商品id来查询该商品是否存在
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            //如果商品不存在则抛出异常
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库（orderMaster 和 OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.descreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String id) {

        OrderMaster orderMaster = orderMasterRepository.findOne(id);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(id);
        if(orderDetailList==null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = byBuyerOpenid.getContent().stream().map(e -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderDTO, e);
            return orderDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(orderDTOList,pageable,byBuyerOpenid.getTotalPages());
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }
}
