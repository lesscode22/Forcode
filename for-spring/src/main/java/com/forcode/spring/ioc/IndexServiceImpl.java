package com.forcode.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-26
 **/
public class IndexServiceImpl implements IndexService {

    @Override
    public List<String> menuList(String clientKey) {
        return Collections.singletonList("message");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("============== bean 创建回调");
    }
}
