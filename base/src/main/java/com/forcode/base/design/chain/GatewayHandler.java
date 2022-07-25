package com.forcode.base.design.chain;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public abstract class GatewayHandler {

    protected GatewayHandler next;

    public GatewayHandler setNext(GatewayHandler next) {
        this.next = next;
        return this;
    }

    public GatewayHandler getNext() {
        return next;
    }

    public abstract void handle();

    public static void main(String[] args) {

        GatewayHandler handlerChain = new ApiLimitGatewayHandler()
                .setNext(new BlacklistGatewayHandler());

        handlerChain.handle();
    }
}
