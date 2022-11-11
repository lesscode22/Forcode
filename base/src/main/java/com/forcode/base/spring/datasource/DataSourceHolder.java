package com.forcode.base.spring.datasource;

import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-10
 **/
@Slf4j
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceEnum> DATA_SOURCE_KEY = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum dsKey) {
        DATA_SOURCE_KEY.set(dsKey);
    }

    public static DataSourceEnum getDataSource() {
        return DATA_SOURCE_KEY.get();
    }

    public static void clearDataSource() {
        DATA_SOURCE_KEY.remove();
    }
}
