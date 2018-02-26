package com.visitor;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 16:03
 */
public class VisitorB extends Visitor {


    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println("男人失败时,闷头喝酒,谁也劝不动");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println("女人失败时,眼泪汪汪,谁也劝不了");
    }
}
