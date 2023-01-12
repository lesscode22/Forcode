package com.forcode.base.test;

import com.forcode.base.spring.datasource.DataSourceHolder;
import com.forcode.base.spring.datasource.aspect.DS;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import com.forcode.base.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("open/api")
public class IndexController {

    @Autowired
    IndexMapper indexMapper;
    @Autowired
    IndexController indexController;

    @Autowired
    List<DataSource> dataSourceList;

    @GetMapping("test2")
    public void test2() {

//        indexController.findUser();
        indexController.findChannel();
    }

//    @DS(DataSourceEnum.QD)
    public void findChannel() {

//        List<Map<String, Object>> channel = indexMapper.findChannel();
//        System.out.println(JsonUtil.toJsonPretty(channel));

//        ChannelVO channelForVO = indexMapper.findChannelForVO();
//        System.out.println(JsonUtil.toJsonPretty(channelForVO));

        DataSourceHolder.setDataSource(DataSourceEnum.QD);
        List<ChannelVO> channelForVOList = indexMapper.findChannelForVOList();
        System.out.println(JsonUtil.toJsonPretty(channelForVOList));
        DataSourceHolder.clearDataSource();

        System.out.println(JsonUtil.toJsonPretty(indexMapper.findUser()));
    }

    @DS
    public void findUser() {
        indexMapper.findUser();
    }

}
