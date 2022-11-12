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
import java.sql.SQLException;

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
    DataSource dataSource;

    @Test
    void run() throws SQLException {
//        handlerChain.exec("", "");
//        pipelineExecutor.acceptSync(new InstanceBuildContext());
//        System.out.println(rootRouter.applyStrategy("1"));
    }
}
