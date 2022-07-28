package com.forcode.base.design.visit;

import com.forcode.base.design.visit.element.Student;
import com.forcode.base.design.visit.element.Teacher;
import com.forcode.base.design.visit.element.User;
import com.forcode.base.design.visit.visitor.Principal;
import com.forcode.base.design.visit.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
public class ApiTest {

    public static void main(String[] args) {

        // 初始化容器, 加入一些元素供访问
        List<User> elements = new ArrayList<>();
        elements.add(new Student("小明", "三好学生"));
        elements.add(new Teacher("张三", "一级教师"));
        elements.add(new Student("小红", "四好学生"));
        elements.add(new Teacher("李四", "二级教师"));

        // 创建访问者
        Visitor visitor = new Principal();
        // 每个元素接受访问者的访问
        elements.forEach(el -> el.accept(visitor));
    }
}
