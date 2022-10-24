package com.forcode.base;

import com.forcode.base.design.chain.v2.HandlerChain;
import com.forcode.base.design.chain.v3.PipelineExecutor;
import com.forcode.base.design.chain.v4.AbstractStrategyRouter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-06
 **/
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private HandlerChain handlerChain;
    @Autowired
    private PipelineExecutor pipelineExecutor;
    @Resource
    private AbstractStrategyRouter<String, String> rootRouter;

    @Test
    void run() {
//        handlerChain.exec("", "");
//        pipelineExecutor.acceptSync(new InstanceBuildContext());
        System.out.println(rootRouter.applyStrategy("1"));
    }
}
