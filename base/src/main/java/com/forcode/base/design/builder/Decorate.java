package com.forcode.base.design.builder;
/**
 * @description: 装修套餐接口
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public interface Decorate {

    Decorate appendCeiling(Matter matter); // 吊顶

    Decorate appendCoat(Matter matter);    // 涂料

    Decorate appendFloor(Matter matter);   // 地板

    String getDetail();                 // 明细
}
