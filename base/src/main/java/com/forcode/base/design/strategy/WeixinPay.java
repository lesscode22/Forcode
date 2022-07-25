package com.forcode.base.design.strategy;

import org.springframework.stereotype.Service;

@PayCode(code = "Weixin")
@Service("weixin1")
public class WeixinPay implements IPay{

    @Override
    public void pay() {
        System.out.println("进入微信支付...");
    }
}
