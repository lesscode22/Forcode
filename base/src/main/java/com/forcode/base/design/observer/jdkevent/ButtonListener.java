package com.forcode.base.design.observer.jdkevent;

import java.util.EventListener;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class ButtonListener implements EventListener {

    public void clickCallback(ButtonEvent source) {
        System.out.println("触发点击监听: " + source);
    }
}
