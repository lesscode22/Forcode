package com.forcode.base;

import com.forcode.base.design.chain.v2.HandlerChain;
import com.forcode.base.design.chain.v3.PipelineExecutor;
import com.forcode.base.design.chain.v4.AbstractStrategyRouter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-06
 **/
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private HandlerChain handlerChain;
    @Autowired
    private PipelineExecutor pipelineExecutor;
    @Resource
    private AbstractStrategyRouter<String, String> rootRouter;
    @Resource
    private JdbcTemplate basicJdbcTemplate;
    @Resource
    private JdbcTemplate orderJdbcTemplate;
    @Autowired
    List<DataSource> dataSourceList;

    @Test
    void run() {
//        handlerChain.exec("", "");
//        pipelineExecutor.acceptSync(new InstanceBuildContext());
//        System.out.println(rootRouter.applyStrategy("1"));

        String sql = "select * from sys_user limit 2";
        List<Map<String, Object>> data = basicJdbcTemplate.queryForList(sql);

        sql = "select * from order_info";
        List<Map<String, Object>> order = orderJdbcTemplate.queryForList(sql);
        System.out.println(order);

    }
}
