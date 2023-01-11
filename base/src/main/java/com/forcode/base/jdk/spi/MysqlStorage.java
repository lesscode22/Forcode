package com.forcode.base.jdk.spi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysqlStorage implements DataStorage{

    @Override
    public String search(String key) {
        log.info("========== MySql Search ==========");
        return null;
    }
}
