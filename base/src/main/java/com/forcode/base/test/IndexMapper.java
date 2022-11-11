package com.forcode.base.test;

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

    @Select("select * from sys_user limit 2")
    List<Map<String, Object>> findSysUser();

    @Select("select * from order_info")
    List<Map<String, Object>> findOrder();
}
