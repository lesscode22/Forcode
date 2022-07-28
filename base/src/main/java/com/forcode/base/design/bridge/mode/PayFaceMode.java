package com.forcode.base.design.bridge.mode;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-26
 **/
public class PayFaceMode implements PayMode{

    @Override
    public void mode() {
        System.out.println("刷脸支付");
    }
}
