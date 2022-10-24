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

    public static class Builder{
        private GatewayHandler head;
        private GatewayHandler tail;

        public Builder addHandler(GatewayHandler handler){
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.setNext(handler);
            this.tail = handler;
            return this;
        }

        public GatewayHandler build(){
            return this.head;
        }
    }

    public static void main(String[] args) {

//        GatewayHandler handlerChain = new ApiLimitGatewayHandler()
//                .setNext(new BlacklistGatewayHandler());
        GatewayHandler handlerChain = new Builder()
                .addHandler(new ApiLimitGatewayHandler())
                .addHandler(new BlacklistGatewayHandler())
                .build();

        handlerChain.handle();
    }
}
