package com.forcode.base.spring.datasource.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 默认连接池参数设置
 * 
 * @author: TJ
 * @date:  2022-11-09
 **/
@Slf4j
@Data
public class DruidPoolProperties {

    public static String url = "";
    public static String username = "";
    public static String password = "";

    // 初始连接数
    public static int initialSize = 5;
    // 最小连接池数量
    public static int minIdle = 10;
    // 最大连接池数量
    public static int maxActive = 20;
    // 配置获取连接等待超时的时间
    public static int maxWait = 60000;
    // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    public static int timeBetweenEvictionRunsMillis = 60000;
    // 配置一个连接在池中最小生存的时间，单位是毫秒
    public static int minEvictableIdleTimeMillis = 300000;
    // 配置一个连接在池中最大生存的时间，单位是毫秒
    public static int maxEvictableIdleTimeMillis = 900000;
    // 配置检测连接是否有效
    public static String validationQuery = "SELECT 1 FROM DUAL";
    public static boolean testWhileIdle = true;
    public static boolean testOnBorrow = false;
    public static boolean testOnReturn = false;
    public static boolean useGlobalDataSourceStat = true;
    public static String connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=3000";
    public static String filters = "stat,wall";
}
