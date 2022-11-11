package com.forcode.base.spring.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 连接池属性
 * 
 * @author: TJ
 * @date:  2022-11-09
 **/
@Slf4j
@Data
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidPoolProperties {

    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int maxEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean useGlobalDataSourceStat;
    private String filters;
    private String connectionProperties;

    private Map<DataSourceEnum, DataSource> dsMap = new HashMap<>();

    public DruidDataSource process(DataSourceEnum key, DruidDataSource datasource) {
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        datasource.setMinIdle(minIdle);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        datasource.setConnectionProperties(connectionProperties);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            log.error("连接池异常: ", e);
        }
        dsMap.put(key, datasource);
        return datasource;
    }
}
