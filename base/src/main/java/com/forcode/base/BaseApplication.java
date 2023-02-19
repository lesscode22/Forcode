package com.forcode.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-30
 **/
@PropertySource(value = {"classpath:extra/sharding-jdbc.properties"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class BaseApplication {

    public static void main(String[] args) {
        System.setProperty("jasypt.encryptor.password", "");
        SpringApplication.run(BaseApplication.class, args);
    }
}
