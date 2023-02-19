package com.forcode.base.spring.datasource.provider;

import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ShardingDataSourceProvider implements DynamicDataSourceProvider{

    // shardingJdbc 默认会创建 ShardingSphereDataSource
    @Resource
    private DataSource shardingSphereDataSource;

    @Override
    public Map<DataSourceEnum, DataSource> loadDataSources() {
        Map<DataSourceEnum, DataSource> map = new HashMap<>();
        map.put(DataSourceEnum.ORDER, shardingSphereDataSource);
        return map;
    }
}
