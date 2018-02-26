package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 15:53
 * 访问者
 */
public abstract class Visitor {

    public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);
    public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
}
