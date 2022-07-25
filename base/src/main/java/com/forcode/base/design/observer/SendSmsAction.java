package com.forcode.base.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 观察者 - 发短信
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class SendSmsAction implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("发送短信>>>");
    }
}
