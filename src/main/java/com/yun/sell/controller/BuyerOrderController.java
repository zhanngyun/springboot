package com.yun.sell.controller;

import com.yun.sell.VO.ResultVO;
import com.yun.sell.converter.OrderForm2OrderDTOConverter;
import com.yun.sell.domain.OrderDetail;
import com.yun.sell.dto.OrderDTO;
import com.yun.sell.enums.ResultEnum;
import com.yun.sell.exception.SellException;
import com.yun.sell.form.OrderForm;
import com.yun.sell.service.BuyerService;
import com.yun.sell.service.OrderMasterService;
import com.yun.sell.utils.ResultVOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzhang
 * @Date: 2018/1/26 10:20
 */
@RestController
@RequestMapping(value="/buyer/order")
public class BuyerOrderController {

    private static final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        //参数判断
        if(bindingResult.hasErrors()){
            logger.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.Cart_EMPTY);
        }
        OrderDTO dto = orderMasterService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",dto.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        if(StringUtils.isEmpty(openid)){
            logger.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,pageSize);
        Page<OrderDTO> orderDTOS = orderMasterService.findByBuyerOpenid(openid, pageRequest);
        return ResultVOUtil.success(orderDTOS.getContent());

    }
    //订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            logger.error("【订单详情】参数不正确，openid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDetail> orderOne = buyerService.findOrderOne(openid, orderId);
        return  ResultVOUtil.success(orderOne);
    }
    //取消订单
    @GetMapping(value = "/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            logger.error("【订单详情】参数不正确，openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
