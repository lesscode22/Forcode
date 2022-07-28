package com.forcode.base.design.visit.element;

import com.forcode.base.design.visit.visitor.Visitor;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
public class Teacher extends User{

    public Teacher(String name, String identity) {
        super(name, identity);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
