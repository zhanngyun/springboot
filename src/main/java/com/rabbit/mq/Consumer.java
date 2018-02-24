package com.rabbit.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeoutException;

/**
 * @Author: yzhang
 * @Date: 2018/2/23 17:38
 * 消费者
 */
public class Consumer {

    //交换器名称
    private final static String DIRECT_EXCHANGE_NAME = "exchange_direct";
    private final static String FANOUT_EXCHANGE_NAME = "exchange_fanout";
    public static void main(String...args) throws IOException, TimeoutException {
        // 获取不同的pid,方便标识不同的消费者
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];

        //建立到代理服务器到连接
        Connection conn = ConnectionUtil.getConn();

        //获取信道
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME,"direct");
        //声明队列
        String queueName = channel.queueDeclare().getQueue();

        String routingkey = "hello";

        //绑定队列,通过hola将队列和交换器绑定起来
        channel.queueBind(queueName,DIRECT_EXCHANGE_NAME,routingkey);

        System.out.println(pid + "已经创建,正在等待消息...");

        while(true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    //消费的路由键
                    String routingKey = envelope.getRoutingKey();
                    //消费的内容类型
                    String contentType = properties.getContentType();
                    //确认消息
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(pid + "接收到了消息: " + bodyStr);

                }
            });
        }
    }
}
