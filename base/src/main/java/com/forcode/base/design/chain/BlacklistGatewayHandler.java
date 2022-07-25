package com.forcode.base.design.chain;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class BlacklistGatewayHandler extends GatewayHandler {

    @Override
    public void handle() {
        System.out.println("黑名单校验");

        if (getNext() != null) {
            getNext().handle();
        }
    }
}
