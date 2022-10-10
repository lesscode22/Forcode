package com.forcode.base.design.chain.v2;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-06
 **/
@Component
@Order(1)
public class CheckBlankHandler extends AbstractRebateHandler{

    @Override
    void doCheck(Object request, Object response) {
        System.out.println("CheckBlankHandler ===");
    }
}
