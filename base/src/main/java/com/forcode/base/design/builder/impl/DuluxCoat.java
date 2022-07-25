package com.forcode.base.design.builder.impl;

import com.forcode.base.design.builder.Matter;

import java.math.BigDecimal;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class DuluxCoat implements Matter {

    public String scene() {
        return "涂料";
    }

    public String brand() {
        return "多乐士(Dulux)";
    }

    public String model() {
        return "第二代";
    }

    public BigDecimal price() {
        return new BigDecimal(719);
    }

    public String desc() {
        return "多乐士是阿克苏诺贝尔旗下的著名建筑装饰油漆品牌，产品畅销于全球100个国家，每年全球有5000万户家庭使用多乐士油漆。";
    }
}
