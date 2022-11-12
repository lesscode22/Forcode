package com.forcode.base.spring.datasource.config;

import com.forcode.base.spring.datasource.DynamicDataSource;
import com.forcode.base.spring.datasource.provider.DynamicDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DynamicDataSourceProvider dataSourceProvider;

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        return new DynamicDataSource(dataSourceProvider);
    }

}
