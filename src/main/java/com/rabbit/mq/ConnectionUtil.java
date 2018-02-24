package com.rabbit.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: yzhang
 * @Date: 2018/2/24 13:35
 */
public class ConnectionUtil {

    private static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    private static Connection conn = null;

    //获取连接
    public static Connection getConn() {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("sunCloud");
        factory.setPassword("sunCloud");
        //设置Rabbit地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        try{
            conn = factory.newConnection();
        }catch (Exception e){
            logger.error("【rabbitMQ】获取连接失败,{}",e);
            e.printStackTrace();
        }
        return conn;

    }

    //关闭连接
    public static void closeConn(){
        if(conn!=null){
            try{
                conn.close();
            }catch (IOException e){
                logger.error("【rabbitMQ】关闭连接失败,{}",e);
                e.printStackTrace();
            }
        }
    }
}
