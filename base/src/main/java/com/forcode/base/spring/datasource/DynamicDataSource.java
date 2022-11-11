package com.forcode.base.spring.datasource;

import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import com.forcode.base.spring.datasource.provider.DynamicDataSourceProvider;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 *
 * @author: TJ
 * @date:  2022-11-07
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

    public DynamicDataSource(DynamicDataSourceProvider dataSourceProvider) {
        Map<Object, Object> map = new HashMap<>(dataSourceProvider.loadDataSources());
        super.setTargetDataSources(map);
        super.setDefaultTargetDataSource(map.get(DataSourceEnum.DEFAULT.getKey()));
        super.afterPropertiesSet();
    }
}
