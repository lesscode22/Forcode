package com.forcode.base.spring.datasource.provider;

import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import com.forcode.base.spring.datasource.config.DruidPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @description: yml文件配置数据源
 * 
 * @author: TJ
 * @date:  2022-11-11
 **/
@Component
public class YamlDynamicDataSourceProvider implements DynamicDataSourceProvider {

    @Autowired
    private DruidPoolProperties poolProperties;

    @Override
    public Map<DataSourceEnum, DataSource> loadDataSources() {
        return poolProperties.getDsMap();
    }
}
