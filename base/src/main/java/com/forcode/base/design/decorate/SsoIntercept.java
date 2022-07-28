package com.forcode.base.design.decorate;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-27
 **/
public class SsoIntercept implements GlobalIntercept{

    @Override
    public void preHandle(String request, String response) {

        System.out.println("sso登录拦截");
    }
}
