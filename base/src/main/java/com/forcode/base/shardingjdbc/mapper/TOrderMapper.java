package com.forcode.base.shardingjdbc.mapper;

import com.forcode.base.shardingjdbc.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TJ
 * @since 2023-02-19
 */
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

    @Select("select * from t_order where order_id in (1, 2)")
    List<Map<String, Object>> queryOrderForIn();
}
