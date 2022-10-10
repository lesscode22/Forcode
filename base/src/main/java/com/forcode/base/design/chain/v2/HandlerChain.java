package com.forcode.base.design.chain.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description:
 * @author: TJ
 * @date: 2022-10-06
 **/
@Component
public class HandlerChain {

    // 自动注入各个责任链的对象
    @Autowired
    private List<AbstractRebateHandler> abstractHandleList;

    private AbstractRebateHandler abstractHandler;

    // spring注入后自动执行，责任链的对象连接起来
    @PostConstruct
    public void initializeChainFilter() {

        for (int i = 0; i < abstractHandleList.size(); i++) {
            if (i == 0) {
                abstractHandler = abstractHandleList.get(0);
            } else {
                AbstractRebateHandler currentHandler = abstractHandleList.get(i - 1);
                AbstractRebateHandler nextHandler = abstractHandleList.get(i);
                currentHandler.setNextHandler(nextHandler);
            }
        }
    }

    // 直接调用这个方法使用
    public Object exec(Object request, Object response) {
        abstractHandler.check(request, response);
        return response;
    }
}
