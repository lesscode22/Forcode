package com.forcode.base.design.visit.visitor;

import com.forcode.base.design.visit.element.Student;
import com.forcode.base.design.visit.element.Teacher;

/**
 * @description: 抽象访问者
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
public interface Visitor {

    void visit(Student student);

    void visit(Teacher teacher);
}
