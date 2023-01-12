package com.forcode.base.test;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChannelVO {

    private Long pId;
    private String channelCode;
    private String channelName;
    private Date createTime;
//    private LocalDateTime updateTime;
}
