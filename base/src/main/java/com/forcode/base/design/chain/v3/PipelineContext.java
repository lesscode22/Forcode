package com.forcode.base.design.chain.v3;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @description: 传递到管道的上下文
 * 
 * @author: TJ
 **/
@Getter
@Setter
public class PipelineContext {

    /**
     * 处理开始时间
     */
    private LocalDateTime startTime;

    /**
     * 处理结束时间
     */
    private LocalDateTime endTime;
}
