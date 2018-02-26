package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 16:03
 */
public class VisitorA extends Visitor {


    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println("男人成功时,背后多半有一个伟大的女人");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println("女人成功时,背后大多有一个不成功的男人");
    }
}
