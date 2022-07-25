package com.forcode.base.design.builder;

import java.math.BigDecimal;

/**
 * @description: 装修物料接口
 *
 * @author: TJ
 * @date:  2022-06-03
 **/
public interface Matter {

    String scene();      // 场景；地板、涂料、吊顶

    String brand();      // 品牌

    String model();      // 型号

    BigDecimal price();  // 价格

    String desc();       // 描述

}
