package com.forcode.spring.ioc;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-26
 **/
public interface IndexService extends BeanFactoryPostProcessor, BeanPostProcessor {

    List<String> menuList(String clientKey);
}
