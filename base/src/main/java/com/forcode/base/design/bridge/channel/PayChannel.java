package com.forcode.base.design.bridge.channel;

import com.forcode.base.design.bridge.mode.PayMode;

/**
 * @description: 支付方式
 *
 * 桥接模式中的抽象化部分
 * 
 * @author: TJ
 * @date:  2022-07-26
 **/
public abstract class PayChannel {

    public PayMode payMode;

    public void setPayMode(PayMode mode) {
        this.payMode = mode;
    }

    public abstract void payMoney();
}
