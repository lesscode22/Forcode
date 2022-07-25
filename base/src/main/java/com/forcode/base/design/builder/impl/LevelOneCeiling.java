package com.forcode.base.design.builder.impl;

import com.forcode.base.design.builder.Matter;

import java.math.BigDecimal;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class LevelOneCeiling implements Matter {

    public String scene() {
        return "吊顶";
    }

    public String brand() {
        return "装修公司自带";
    }

    public String model() {
        return "一级顶";
    }

    public BigDecimal price() {
        return new BigDecimal(260);
    }

    public String desc() {
        return "造型只做低一级，只有一个层次的吊顶，一般离顶120-150mm";
    }
}
