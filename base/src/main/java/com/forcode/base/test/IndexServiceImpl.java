package com.forcode.base.test;

import com.forcode.base.spring.datasource.aspect.DS;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-12
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private IndexService indexService;

    @Override
    public void execute() {
        indexService.findOrder();
        indexService.findUser();
    }

    @DS(DataSourceEnum.ORDER)
    @Override
    public void findOrder() {
        System.out.println(indexMapper.findOrder());
    }

    @DS
    @Override
    public void findUser() {
        System.out.println(indexMapper.findSysUser());
    }
}
