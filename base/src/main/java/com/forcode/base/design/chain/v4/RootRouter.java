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
public class RootRouter extends AbstractStrategyRouter<String, String>{

    @Resource
    private StrategyHandler<String, String> middleHandlerA;

    @Override
    protected StrategyMapper<String, String> registerStrategyMapper() {
        return param -> middleHandlerA;
    }
}
