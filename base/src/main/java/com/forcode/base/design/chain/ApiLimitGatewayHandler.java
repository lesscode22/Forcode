package com.forcode.base.design.chain;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class ApiLimitGatewayHandler extends GatewayHandler {

    @Override
    public void handle() {
        System.out.println("api接口限流");

        if (getNext() != null) {
            getNext().handle();
        }
    }
}
