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
public class Ordor {

    //交换器名称
    private final static String DIRECT_EXCHANGE_NAME = "exchange_direct";
    private final static String FANOUT_EXCHANGE_NAME = "exchange_fanout";

    public static void main(String...args) throws IOException, TimeoutException, InterruptedException {

        // 获取不同的pid,方便标识不同的消费者
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        Connection conn = ConnectionUtil.getConn();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");
        // 由RabbitMQ自行创建的临时队列,唯一且随消费者的中止而自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
        String routingkey = "";

        System.out.println(pid + "已经创建,正在等待消息...");
        //绑定队列,通过hola将队列和交换器绑定起来
        channel.queueBind(queueName,DIRECT_EXCHANGE_NAME,routingkey);
        while(true) {
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
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
