package com.forcode.base.spring.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.forcode.base.spring.datasource.aspect.DataSourceEnum;
import com.forcode.base.spring.datasource.DynamicDataSource;
import com.forcode.base.spring.datasource.provider.DynamicDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DynamicDataSourceProvider dataSourceProvider;

    @Bean
    @ConfigurationProperties("spring.datasource.ds.basic")
    public DataSource basicDataSource(DruidPoolProperties poolProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return poolProperties.process(DataSourceEnum.DEFAULT, dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds.order")
    public DataSource orderDataSource(DruidPoolProperties poolProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return poolProperties.process(DataSourceEnum.ORDER, dataSource);
    }

    @Bean
    @Primary
    @DependsOn({"basicDataSource", "orderDataSource"})
    public DataSource dynamicDataSource() {
        return new DynamicDataSource(dataSourceProvider);
    }

    // --------------------------------------------------

    @Bean
    public JdbcTemplate basicJdbcTemplate(@Qualifier("basicDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public FilterRegistrationBean<Filter> removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");

        final String filePath = "support/http/resources/js/common.js";

        //创建filter进行过滤
        Filter filter = new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy() {
            }
        };
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }


}
