package com.yun.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 16:12
 */
public interface BaseService<T,M> {
    T findOne(M id);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    T save(T t);

}
