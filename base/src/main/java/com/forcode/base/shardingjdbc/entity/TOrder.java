package com.forcode.base.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author TJ
 * @since 2023-02-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_order")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 用户 id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 订单价格
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
