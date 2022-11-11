package com.forcode.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-30
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
