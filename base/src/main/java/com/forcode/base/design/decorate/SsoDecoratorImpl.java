package com.forcode.base.design.decorate;
/**
 * @description: 具体装饰实现
 * 
 * @author: TJ
 * @date:  2022-07-27
 **/
public class SsoDecoratorImpl extends SsoDecorator {

    public SsoDecoratorImpl(GlobalIntercept intercept) {
        super(intercept);
    }

    @Override
    public void preHandle(String request, String response) {
        super.preHandle(request, response);
        System.out.println("sso登录++++装饰");
    }

    public static void main(String[] args) {
        GlobalIntercept ssoIntercept = new SsoIntercept();

        GlobalIntercept ssoDecorator = new SsoDecoratorImpl(ssoIntercept);
        ssoDecorator.preHandle("a", "b");
    }
}
