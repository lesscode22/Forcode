package com.forcode.base.test;

import com.forcode.base.spring.datasource.aspect.DS;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

/**
 * @description:
 *
 * @author: TJ
 * @date:  2022-07-25
 **/
@RestController
@RequestMapping("api")
public class IndexController {

//    @Autowired
//    private IndexService indexService;
    @Autowired
    IndexMapper indexMapper;
    @Autowired
    IndexController indexController;

    @Autowired
    List<DataSource> dataSourceList;

    @RequestMapping("test2")
    public void test2() {
//        indexService.execute();

        indexController.findOrder();
        indexController.findUser();
    }

    @DS(DataSourceEnum.ORDER)
    public void findOrder() {
//        System.out.println(indexMapper.findOrder());
        indexMapper.insertOrder();
    }

    @DS
    public void findUser() {
//        System.out.println(indexMapper.findSysUser());
        indexMapper.insertDataTest();
    }

}
