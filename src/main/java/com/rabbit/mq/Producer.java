package com.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @Author: yzhang
 * @Date: 2018/2/23 17:27
 * 生产者
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);


    //交换器名称
    private final static String DIRECT_EXCHANGE_NAME = "exchange_direct";//也可以为""(默认状态下)
    private final static String FANOUT_EXCHANGE_NAME = "exchange_fanout";
    private final static String TOPIC_EXCHANGE_NAME = "exchange_topic";
    public static void main(String...args) throws IOException, TimeoutException,InterruptedException {

        //获取连接
        Connection conn = ConnectionUtil.getConn();
        //获取信道
        Channel channel = conn.createChannel();
        //发布消息内容
        byte[] content = "15038703422".getBytes();
        //使用direct方式
//        useDirectMethod(channel,content);
//        useFanoutMethod(channel,content);
//        userTopicMethod(channel,content);
        userTopicMethodSynchronized(channel,content);
        channel.close();
        ConnectionUtil.closeConn();
    }

    /**
     * 使用direct的方式
     * 任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
     * 一般情况可以使用rabbitMQ自带的Exchange：”"(该Exchange的名字为空字符串，下文称其为default Exchange)。
     * 这种模式下不需要将Exchange进行任何绑定(binding)操作
     * 消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
     * 如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
     *
     * @param channel
     * @param messageBodyBytes
     * @throws IOException
     */
    private static void useDirectMethod(Channel channel,byte[] messageBodyBytes) throws IOException,InterruptedException {
        //设置交换器类型
        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME,"direct");
        //设置routingkey值,这个direct需要,而fanout不需要
        String routingkey = "hello";
        channel.basicPublish(DIRECT_EXCHANGE_NAME,routingkey,null,messageBodyBytes);
        if(channel.waitForConfirms()){
            logger.error("消息发送成功!");
        }if(!channel.waitForConfirms()){
            logger.error("消息发送失败,请重新发送!");
        }
    }

    /**
     * 使用fanout的方式
     * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上
     * 这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定
     * 这种模式不需要RouteKey
     * 如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
     * @param channel
     * @param messageBodyBytes
     * @throws IOException
     */
    private static void useFanoutMethod(Channel channel,byte[] messageBodyBytes) throws IOException {
        //设置交换器类型
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME,"direct",true);
        channel.basicPublish(FANOUT_EXCHANGE_NAME,"",null,messageBodyBytes);
    }

    /**
     * 使用topic的方式
     * 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
     * 这种模式较为复杂，简单来说，就是每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)，Exchange会将消息转发到所有关注主题能与RouteKey模糊匹配的队列。
     * 这种模式需要RouteKey，也许要提前绑定Exchange与Queue。
     * 在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)。
     * “#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。
     * 同样，如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
     * @param channel
     * @param messageBodyBytes
     * @throws IOException
     */
    private static void userTopicMethod(Channel channel,byte[] messageBodyBytes) throws IOException,InterruptedException{
        //声明exchange
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME,"topic");
        //其中item.delete是RouteKey
        channel.confirmSelect();
        //一条
        //channel.basicPublish(TOPIC_EXCHANGE_NAME,"item.delete",null,messageBodyBytes);

        //批量
        for(int i=0;i<10;i++){
            channel.basicPublish(TOPIC_EXCHANGE_NAME,"item.delete",null,messageBodyBytes);
        }

        if(channel.waitForConfirms()){
            logger.error("消息发送成功!");
        }if(!channel.waitForConfirms()){
            logger.error("消息发送失败,请重新发送!");
        }
    }

    /**
     * 异步confirm模式的编程实现最复杂，Channel对象提供的ConfirmListener()回调方法只包含deliveryTag（当前Chanel发出的消息序号），
     * 我们需要自己为每一个Channel维护一个unconfirm的消息序号集合，每publish一条数据，集合中元素加1，每回调一次handleAck方法，
     * unconfirm集合删掉相应的一条（multiple=false）或多条（multiple=true）记录。从程序运行效率上看，这个unconfirm集合最好采用有序集合SortedSet存储结构。
     * 实际上，SDK中的waitForConfirms()方法也是通过SortedSet维护消息序号的。
     * @param channel
     * @param messageBodyBytes
     * @throws IOException
     * @throws InterruptedException
     */
    private static void userTopicMethodSynchronized(Channel channel,byte[] messageBodyBytes) throws IOException,InterruptedException{
        SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        //声明exchange
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME,"topic");
        //其中item.delete是RouteKey
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
                logger.info("消息发送成功!");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
                logger.error("消息发送失败,请重新发送!");
            }
        });
        boolean flag = Boolean.TRUE;
        while (flag) {
            flag = Boolean.FALSE;
            long nextSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish(TOPIC_EXCHANGE_NAME,"item.delete",null,messageBodyBytes);
            confirmSet.add(nextSeqNo);
        }


    }
}
