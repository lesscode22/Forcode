package com.forcode.base.spring.datasource.provider;

import com.forcode.base.spring.datasource.aspect.DataSourceEnum;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @description: 加载数据源
 * 
 * @author: TJ
 * @date:  2022-11-10
 **/
public interface DynamicDataSourceProvider {

    /**
     * 加载所有的数据源
     */
    Map<DataSourceEnum, DataSource> loadDataSources();
}
