package com.yun.sell.controller;

import com.yun.sell.VO.ProductInfoVO;
import com.yun.sell.VO.ProductVO;
import com.yun.sell.VO.ResultVO;
import com.yun.sell.domain.ProductCategory;
import com.yun.sell.domain.ProductInfo;
import com.yun.sell.service.ProductCategoryService;
import com.yun.sell.service.ProductInfoService;
import com.yun.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @Author: yzhang
 * @Date: 2018/1/25 9:04
 */
@RestController
@RequestMapping(value="/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productUpList = productInfoService.findProductUp();
        //2.查询类目(一次查询)
        List<Integer> categoryTypeList = productUpList.stream().map(e -> e.getCategoryType()).distinct().collect(Collectors.toList());
        List<ProductCategory> categoryTypeResult = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装 遍历类目，将查询出来的商品分别对应到相应的类目下面
        List<ProductVO> productVOList = new ArrayList<>();
        categoryTypeResult.forEach(e->{
            List<ProductInfoVO> productInfoList = new ArrayList<>();
            productUpList.forEach(pro ->{
                ProductInfoVO productInfoVO = new ProductInfoVO();
                if(e.getCategoryType().equals(pro.getCategoryType())){
                    BeanUtils.copyProperties(pro,productInfoVO);
                    productInfoList.add(productInfoVO);
                }
            });
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(e.getCategoryName());
            productVO.setCategoryType(e.getCategoryType());
            productVO.setProductInfoVOList(productInfoList);
            productVOList.add(productVO);
        });
        return ResultVOUtil.success(productVOList);
    }

}
