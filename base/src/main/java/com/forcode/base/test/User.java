package com.forcode.base.test;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-21
 **/
@Data
public class User {

    private Long id;

    private BigDecimal money;
    private Double point;

    /**
     * JsonFormat: 局部配置 Date 输出格式
     */
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTimeV1;
    private LocalDateTime createTimeV2;

    private String user_job;
}
