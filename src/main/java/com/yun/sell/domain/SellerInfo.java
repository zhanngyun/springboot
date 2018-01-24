package com.yun.sell.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: yzhang
 * @Date: 2018/1/24 15:29
 */
@Entity
@DynamicUpdate
@Data
public class SellerInfo {

    /**
     * 卖价编号
     */
    @Id
    private String id;
    /**
     * 卖家用户名
     */
    private String username;
    /**
     * 卖家密码
     */
    private String password;
    /**
     * 微信openid
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public SellerInfo() {    }

    public SellerInfo(String id, String username, String password, String openid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}
