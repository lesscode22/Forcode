package com.forcode.base.design.chain.v3;

import org.springframework.stereotype.Component;

/**
 * @description: 上下文处理器实现
 * 
 * @author: TJ
 **/
@Component
public class RepeatContextHandler implements ContextHandler<InstanceBuildContext>{

    @Override
    public boolean handle(InstanceBuildContext context) {
        System.out.println("RepeatContextHandler for InstanceBuildContext ===========");
        return true;
    }
}
