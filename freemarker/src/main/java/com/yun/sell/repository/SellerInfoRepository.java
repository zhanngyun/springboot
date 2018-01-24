package com.yun.sell.repository;

import com.yun.sell.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 买家信息
 * @Author: yzhang
 * @Date: 2018/1/24 15:33
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {



}
