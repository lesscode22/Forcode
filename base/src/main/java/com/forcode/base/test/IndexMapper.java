package com.forcode.base.test;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-11
 **/
@Mapper
public interface IndexMapper {

    @Select("select * from data_test limit 1")
    List<Map<String, Object>> findUser();

    @Select("select * from jc_channel_archives where rownum = 1")
    List<Map<String, Object>> findChannel();

    @Select("select * from jc_channel_archives where rownum = 1")
    ChannelVO findChannelForVO();

    @Select("select * from jc_channel_archives where rownum < 3")
    List<ChannelVO> findChannelForVOList();
}
