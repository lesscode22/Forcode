package com.forcode.base.design.observer.jdkevent;

import java.util.EventObject;

/**
 * @description: 定义事件, 用于封装事件源及一些相关参数
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class ButtonEvent extends EventObject {

    public String clickUserName;
    public Long clickTime;

    public ButtonEvent(ClickSource click) {
        super(click);
        this.clickUserName = "A";
        this.clickTime = System.currentTimeMillis();
    }
}
