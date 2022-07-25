package com.forcode.base.design.observer;

import java.util.Observable;

/**
 * @description: 会员注册后通知: 1.发微信模板消息; 2.发短信
 *
 * @author: TJ
 * @date:  2022-06-04
 **/
public class MemberService extends Observable {

    public void register() {
        System.out.println("会员注册成功");

        super.setChanged();
        super.notifyObservers();
    }

    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        // 添加观察者
        memberService.addObserver(new SendSmsAction());
        memberService.addObserver(new WxTemplateAction());

        memberService.register();
    }
}
