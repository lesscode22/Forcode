package com.forcode.base.design.strategy;

import org.springframework.stereotype.Service;

@PayCode(code = "Alia")
@Service("aliaPay1")
public class AliaPay implements IPay{

    @Override
    public void pay() {
        System.out.println("进入支付宝支付...");
    }
}
