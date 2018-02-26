package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 15:56
 * 元素
 */
public abstract class Element {

    public abstract void accept(Visitor visitor);
}
