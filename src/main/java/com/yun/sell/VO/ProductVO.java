package com.yun.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yun.sell.domain.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目） 返回前台的数据
 * @Author: yzhang
 * @Date: 2018/1/25 9:14
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
