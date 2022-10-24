package com.forcode.base.design.chain.v4;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-24
 **/
@Component
public class MiddleHandlerA extends AbstractStrategyRouter<String, String> implements StrategyHandler<String, String>{

    @Resource
    private StrategyHandler<String, String> lastHandlerB;

    @Override
    protected StrategyMapper<String, String> registerStrategyMapper() {
        return param -> lastHandlerB;
    }

    @Override
    public String apply(String param) {
        System.out.println(">>>>>>>>>>>>>>> MiddleHandlerA running");

        return this.applyStrategy(param);
    }
}
