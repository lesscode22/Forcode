package com.forcode.base.shardingjdbc.service.impl;

import com.forcode.base.shardingjdbc.entity.TOrder;
import com.forcode.base.shardingjdbc.mapper.TOrderMapper;
import com.forcode.base.shardingjdbc.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TJ
 * @since 2023-02-19
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
