package com.forcode.base.design.chain.v3;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 管道路由
 * 构建上下文与管道的映射
 *
 * @author: TJ
 **/
@Configuration
@SuppressWarnings("rawtypes")
public class PipelineRouteConfig implements ApplicationContextAware {

    private ApplicationContext appContext;

    // 上下文类型 -> 上下文对应的处理器
    private static final Map<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler>>> PIPELINE_ROUTE = new HashMap<>();

    // 配置各个上下文及其需要的处理器
    static {
        PIPELINE_ROUTE.put(InstanceBuildContext.class,
                                Arrays.asList(RetryContextHandler.class,
                                        RepeatContextHandler.class)
        );
    }

    // 便于注入路由表使用
    @Bean("pipelineRouteMap")
    public Map<Class<? extends PipelineContext>, List<? extends ContextHandler>> getHandlerPipelineMap() {
        return PIPELINE_ROUTE.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::toPipeline));
    }

    private List<? extends ContextHandler> toPipeline(
            Map.Entry<Class<? extends PipelineContext>, List<Class<? extends ContextHandler>>> entry) {
        return entry.getValue()
                .stream()
                .map(appContext::getBean)
                .collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
