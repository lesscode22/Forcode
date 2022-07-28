package com.forcode.base.design.bridge;

import com.forcode.base.design.bridge.channel.PayChannel;
import com.forcode.base.design.bridge.channel.WxPayChannel;
import com.forcode.base.design.bridge.mode.PayFaceMode;
import com.forcode.base.design.bridge.mode.PayFingerprintMode;

/**
 * @description:
 *
 * 桥接模式: 将抽象部分与实现部分分离
 *      把多种可匹配的使用进行组合。说白了核心实现也就是在A类中含有B类接口，通过构造函数传递B类的实现，这个B类就是桥
 * 使用场景：
 *      当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时;
 *      当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时;
 *
 * @author: TJ
 * @date:  2022-07-26
 **/
public class ApiTest {

    public static void main(String[] args) {
        // 支付渠道
        PayChannel channel = new WxPayChannel();
        // 支付模式
        channel.setPayMode(new PayFingerprintMode());
        // 支付
        channel.payMoney();
    }
}
