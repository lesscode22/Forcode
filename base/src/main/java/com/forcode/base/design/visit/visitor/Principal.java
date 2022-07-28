package com.forcode.base.design.visit.visitor;

import com.forcode.base.design.visit.element.Student;
import com.forcode.base.design.visit.element.Teacher;

/**
 * @description: 校长访问
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
public class Principal implements Visitor{

    @Override
    public void visit(Student student) {
        System.out.println("校长访问学生>>>>> " + student.getName() + ": " + student.getIdentity());
    }

    @Override
    public void visit(Teacher teacher) {
        System.out.println("校长访问老师===== " + teacher.getName() + ": " + teacher.getIdentity());
    }
}
