package com.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yzhang
 * @Date: 2018/2/26 16:08
 */
public class ObjectStract {

    private  List<Element> elements = new ArrayList<>();

    //增加
    public void attach(Element element){
        elements.add(element);
    }

    //移除
    public void detach(Element element){
        elements.remove(element);
    }

    //查看显示
    public void display(Visitor visitor){
        for (Element e:elements) {
            e.accept(visitor);
        }
    }

    public static void main(String ... args){

        ConcreteElementA concreteElementA = new ConcreteElementA();
        ConcreteElementB concreteElementB = new ConcreteElementB();

        ObjectStract objectStract = new ObjectStract();
        objectStract.attach(concreteElementA);
        objectStract.attach(concreteElementB);

        VisitorA visitorA = new VisitorA();
        VisitorB visitorB = new VisitorB();
        VisitorC visitorC = new VisitorC();

        objectStract.display(visitorA);
        objectStract.display(visitorB);
        objectStract.display(visitorC);



    }
}
