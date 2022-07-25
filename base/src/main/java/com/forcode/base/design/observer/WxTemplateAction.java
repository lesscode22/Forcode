package com.forcode.base.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 观察者 - 发模板消息
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class WxTemplateAction implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("发送模板消息>>>");
    }
}
