package com.forcode.base.design.fsm.v2;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-16
 **/
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderChangeEvent> {

    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderChangeEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatusEnum.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 配置状态流转
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderChangeEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatusEnum.WAIT_PAYMENT)
                .target(OrderStatusEnum.WAIT_DELIVER)
                .event(OrderChangeEvent.PAYED)

                .and().withExternal()
                .source(OrderStatusEnum.WAIT_DELIVER)
                .target(OrderStatusEnum.WAIT_RECEIVE)
                .event(OrderChangeEvent.DELIVERY)

                .and().withExternal()
                .source(OrderStatusEnum.WAIT_RECEIVE)
                .target(OrderStatusEnum.FINISH)
                .event(OrderChangeEvent.RECEIVED);
    }
}
