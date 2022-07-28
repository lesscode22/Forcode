package com.forcode.base.design.bridge.channel;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-26
 **/
public class ZfbPayChannel extends PayChannel{

    @Override
    public void payMoney() {
        System.out.println("支付渠道: 支付宝");
        payMode.mode();
    }
}
