package com.forcode.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-26
 **/
public class IOCTest {

    public static void main(String[] args) {
        // 用配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        IndexService indexService = context.getBean(IndexService.class);
        System.out.println(indexService.menuList("a"));
    }
}
