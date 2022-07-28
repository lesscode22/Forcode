package com.forcode.base.design.decorate;
/**
 * @description: 单点登录装饰器
 * 
 * @author: TJ
 * @date:  2022-07-27
 **/
public class SsoDecorator implements GlobalIntercept{

    private GlobalIntercept intercept;

    public SsoDecorator(GlobalIntercept it) {
        this.intercept = it;
    }

    @Override
    public void preHandle(String request, String response) {
        intercept.preHandle(request, response);
    }
}
