package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 15:57
 */
public class ConcreteElementA extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }
}
