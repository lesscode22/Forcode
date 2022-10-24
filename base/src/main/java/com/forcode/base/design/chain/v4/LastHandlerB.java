package com.forcode.base.design.chain.v4;

import org.springframework.stereotype.Component;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-24
 **/
@Component
public class LastHandlerB implements StrategyHandler<String, String>{

    @Override
    public String apply(String param) {
        System.out.println(">>>>>>>>> LastHandlerB running");
        return "return = " + param;
    }
}
