package com.forcode.base.design.builder;

import com.forcode.base.design.builder.impl.*;

/**
 * @description: 装修风格建造
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class DecorateBuilder {

    public Decorate levelOne(Double area) {
        return new DecorateImpl(area, "豪华欧式")
                .appendCeiling(new LevelTwoCeiling())    // 吊顶，二级顶
                .appendCoat(new DuluxCoat())             // 涂料，多乐士
                .appendFloor(new ShengXiangFloor());     // 地板，圣象
    }

    public Decorate levelTwo(Double area){
        return new DecorateImpl(area, "轻奢田园")
                .appendCeiling(new LevelTwoCeiling())   // 吊顶，二级顶
                .appendCoat(new LiBangCoat());           // 涂料，立邦
    }

    public Decorate levelThree(Double area){
        return new DecorateImpl(area, "现代简约")
                .appendCeiling(new LevelOneCeiling())   // 吊顶，二级顶
                .appendCoat(new LiBangCoat());           // 涂料，立邦
    }

    public static void main(String[] args) {
        DecorateBuilder builder = new DecorateBuilder();
        // 创建不同风格的装修方式
        System.out.println(builder.levelOne(12.3).getDetail());
        System.out.println(builder.levelTwo(22.3).getDetail());
        System.out.println(builder.levelThree(32.3).getDetail());
    }
}
