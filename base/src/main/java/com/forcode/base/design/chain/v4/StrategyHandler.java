package com.forcode.base.design.chain.v4;
/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-24
 **/
public interface StrategyHandler<T, R> {

    R apply(T param);
}
