package com.forcode.base.design.observer.jdkevent;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 事件源对象
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class ClickSource {

    public void click() {
        System.out.println("点击按钮ing");

        notifyListeners();
    }

    // 监听器
    List<ButtonListener> listeners = new ArrayList<>();

    // 触发监听
    private void notifyListeners() {
        ButtonEvent event = new ButtonEvent(this);
        listeners.forEach(e -> e.clickCallback(event));
    }

    private void addListeners(ButtonListener listener) {
        listeners.add(listener);
    }

    public static void main(String[] args) {
        ClickSource clickSource = new ClickSource();
        clickSource.addListeners(new ButtonListener());

        clickSource.click();
    }
}
