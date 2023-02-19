package com.forcode.base.shardingjdbc;

import com.forcode.base.common.Result;
import com.forcode.base.shardingjdbc.mapper.TOrderMapper;
import com.forcode.base.shardingjdbc.service.ITOrderService;
import com.forcode.base.spring.datasource.DataSourceHolder;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("open/order")
public class OrderController {

    @Autowired
    private ITOrderService orderService;
    @Autowired
    private TOrderMapper orderMapper;

    @PostMapping("execute")
    public Result execute() {

        DataSourceHolder.setDataSource(DataSourceEnum.ORDER);
        List<Map<String, Object>> data = orderMapper.queryOrderForIn();
        log.info("==== data: {}", data);
        return Result.ok();
    }
}
