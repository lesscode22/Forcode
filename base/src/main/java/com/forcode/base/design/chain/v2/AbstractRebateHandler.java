package com.forcode.base.design.chain.v2;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-06
 **/
public abstract class AbstractRebateHandler {

    // 责任链中的下一个对象
    private AbstractRebateHandler nextHandler;

    abstract void doCheck(Object request, Object response);

    /**
     * 具体参数拦截逻辑, 给子类去实现
     */
    public void check(Object request, Object response) {
        doCheck(request, response);
        if (getNextHandler() != null) {
            getNextHandler().check(request, response);
        }
    }

    /**
     * 责任链的下一个对象
     */
    public void setNextHandler(AbstractRebateHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public AbstractRebateHandler getNextHandler() {
        return nextHandler;
    }
}
