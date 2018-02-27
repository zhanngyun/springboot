package com.reentranrlock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 13:47
 * java自带的锁Reentranlock的深度学习
 */
@RestController
@RequestMapping(value = "/reet")
public class ReentranLockDemo {

    /**
     *实现锁机制的种类:公平锁和非公平锁
     *  非公平锁:当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁
     *  公平锁:当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待.
     */
    ReentrantLock lock = new ReentrantLock();
    static Map<String,Integer> init = new HashMap<>();
    static Map<String,Integer> save = new HashMap<>();
    static {
        init.put("summary",10000);
        save.put("save",0);
    }


    @RequestMapping(value = "/reentanlock")
    public String descRepertory(){
        //加锁
        lock.lock();

        //减库存
        Integer value = init.get("summary");
        value = value-1;
        init.put("summary",value);
        Integer value2 = ReentranLockDemo.save.get("save");
        value2 = value2+1;
        save.put("save",value2);
        //解锁
        lock.unlock();
        return "订单剩余"+value+"件,卖出"+value2+"件,总共10000件";

    }




    public static void main(String...args){

    }
}
