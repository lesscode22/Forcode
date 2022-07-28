package com.forcode.base.design.visit.element;

import com.forcode.base.design.visit.visitor.Visitor;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
public class Student extends User{

    public Student(String name, String identity) {
        super(name, identity);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
