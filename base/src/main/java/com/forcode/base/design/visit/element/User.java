package com.forcode.base.design.visit.element;

import com.forcode.base.design.visit.visitor.Visitor;
import lombok.Data;

/**
 * @description: 抽象元素, 供访问者访问
 * 
 * @author: TJ
 * @date:  2022-07-28
 **/
@Data
public abstract class User {

    private String name;
    // 身份
    private String identity;

    public User(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }

    // 核心访问方法, 传入访问者, 用来访问自己
    public abstract void accept(Visitor visitor);
}
