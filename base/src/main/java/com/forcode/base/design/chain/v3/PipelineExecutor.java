package com.forcode.base.design.chain.v3;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 管道执行器
 * 根据传入的上下文数据的类型，找到其对应的管道，然后将上下文数据放入管道中去进行处理
 *
 * @author: TJ
 **/
@Component
@Slf4j
@SuppressWarnings("all")
public class PipelineExecutor {

    @Resource
    private Map<Class<? extends PipelineContext>, List<? extends ContextHandler>> pipelineRouteMap;

    /**
     * 同步处理输入的上下文数据
     */
    public boolean acceptSync(PipelineContext context) {
        // 上下文类型
        Class<? extends PipelineContext> contextType = context.getClass();
        // 获取数据处理管道
        List<? extends ContextHandler> pipeline = pipelineRouteMap.get(contextType);

        if (CollUtil.isEmpty(pipeline)) return false;

        boolean handleResult = true;
        for (ContextHandler contextHandler : pipeline) {
            try {
                handleResult = contextHandler.handle(context);
            } catch (Exception e) {
                handleResult = false;
                log.error("[{}]上下文执行失败, handler:{}", contextType.getSimpleName(), contextHandler.getClass().getSimpleName(), e);
            }

            // 管道执行失败则停止
            if (!handleResult) break;
        }

        return handleResult;
    }
}
