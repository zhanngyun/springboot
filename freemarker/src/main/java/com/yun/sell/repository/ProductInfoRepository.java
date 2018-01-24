package com.yun.sell.repository;

import com.yun.sell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  产品信息
 * @Author: yzhang
 * @Date: 2018/1/24 14:25
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductInfo> findByProductStatus(Integer productStatus);


}
