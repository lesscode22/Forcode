package com.forcode.base.spring.datasource.provider;

import cn.hutool.core.map.MapUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import com.forcode.base.spring.datasource.config.DruidPoolProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: yml文件配置数据源
 * 
 * @author: TJ
 * @date:  2022-11-11
 **/
@Slf4j
@Data
@Component
@ConfigurationProperties("spring.datasource")
public class YamlDynamicDataSourceProvider implements DynamicDataSourceProvider {

    private Map<String, Map<String, String>> ds = new HashMap<>();

    @Override
    public Map<DataSourceEnum, DataSource> loadDataSources() {
        Map<DataSourceEnum, DataSource> dataMap = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : ds.entrySet()) {
            String k = entry.getKey();
            Map<String, String> prop = entry.getValue();
            DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
            dataSource.setUrl(MapUtil.getStr(prop, "url", DruidPoolProperties.url));
            dataSource.setUsername(MapUtil.getStr(prop, "username", DruidPoolProperties.username));
            dataSource.setPassword(MapUtil.getStr(prop, "password", DruidPoolProperties.password));
            dataSource.setInitialSize(MapUtil.getInt(prop, "initialSize", DruidPoolProperties.initialSize));
            dataSource.setMinIdle(MapUtil.getInt(prop, "minIdle", DruidPoolProperties.minIdle));
            dataSource.setMaxActive(MapUtil.getInt(prop, "maxActive", DruidPoolProperties.maxActive));
            dataSource.setMaxWait(MapUtil.getInt(prop, "maxWait", DruidPoolProperties.maxWait));
            dataSource.setTimeBetweenEvictionRunsMillis(MapUtil.getInt(prop, "timeBetweenEvictionRunsMillis",
                    DruidPoolProperties.timeBetweenEvictionRunsMillis));
            dataSource.setMinEvictableIdleTimeMillis(MapUtil.getInt(prop, "minEvictableIdleTimeMillis",
                    DruidPoolProperties.minEvictableIdleTimeMillis));
            dataSource.setMaxEvictableIdleTimeMillis(MapUtil.getInt(prop, "maxEvictableIdleTimeMillis",
                    DruidPoolProperties.maxEvictableIdleTimeMillis));
            dataSource.setValidationQuery(MapUtil.getStr(prop, "validationQuery", DruidPoolProperties.validationQuery));
            dataSource.setTestWhileIdle(MapUtil.getBool(prop, "testWhileIdle", DruidPoolProperties.testWhileIdle));
            dataSource.setTestOnBorrow(MapUtil.getBool(prop, "testOnBorrow", DruidPoolProperties.testOnBorrow));
            dataSource.setTestOnReturn(MapUtil.getBool(prop, "testOnReturn", DruidPoolProperties.testOnReturn));
            dataSource.setUseGlobalDataSourceStat(MapUtil.getBool(prop, "useGlobalDataSourceStat",
                    DruidPoolProperties.useGlobalDataSourceStat));
            dataSource.setConnectionProperties(MapUtil.getStr(prop, "connectionProperties",
                    DruidPoolProperties.connectionProperties));
            try {
                dataSource.setFilters(MapUtil.getStr(prop, "filters", DruidPoolProperties.filters));
                dataSource.init();
            } catch (SQLException e) {
                log.error("连接池异常: ", e);
                throw new RuntimeException("======= 数据库初始化异常 ==========");
            }
            dataMap.put(DataSourceEnum.of(k), dataSource);
        }
        return dataMap;
    }
}
