package com.forcode.base.design.decorate;
/**
 * @description: 全局拦截器
 * 
 * @author: TJ
 * @date:  2022-07-27
 **/
public interface GlobalIntercept {

    void preHandle(String request, String response);
}
