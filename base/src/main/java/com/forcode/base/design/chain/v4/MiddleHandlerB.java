package com.forcode.base.design.chain.v4;

import org.springframework.stereotype.Component;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-24
 **/
@Component
public class MiddleHandlerB extends AbstractStrategyRouter<String, String> implements StrategyHandler<String, String> {

    @Override
    protected StrategyMapper<String, String> registerStrategyMapper() {
        return param -> new LastHandlerA();
    }

    @Override
    public String apply(String param) {
        System.out.println(">>>>>>>>>>>>>>> MiddleHandlerB running");
        return null;
    }
}
