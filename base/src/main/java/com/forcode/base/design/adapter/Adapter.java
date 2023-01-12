package com.forcode.base.design.adapter;

/**
 * @description: 适配器：实现目标接口, 持有需要适配到目标接口的对象
 * 
 * @author: TJ
 * @date:  2022-07-11
 **/
public class Adapter implements Target {

    // 持有适配者
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void debug() {
        adaptee.oldDebug();
    }

    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.debug();
    }
}
