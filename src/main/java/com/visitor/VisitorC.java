package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 16:03
 */
public class VisitorC extends Visitor {


    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println("男人恋爱时,凡事不懂也要装懂");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println("女人恋爱时,遇事懂也装不懂");
    }
}
