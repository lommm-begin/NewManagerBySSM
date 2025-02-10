package com.example.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.web.datasource.routing.DynamicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${oracle.driver-class-name}")
    private String oracleDriver;

    @Value("${oracle.url}")
    private String oracleUrl;

    @Value("${oracle.username}")
    private String oracleUsername;

    @Value("${oracle.password}")
    private String oraclePassword;

    /**
     * 默认提供mysql和Oracle的数据源
     *
     * 默认使用mysql
     * @return
     */
    @Bean
    public DataSource dataSourceByMysql() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        DruidDataSource druidDataSourceByMysql = new DruidDataSource();
        druidDataSourceByMysql.setDriverClassName(driver);
        druidDataSourceByMysql.setUrl(url);
        druidDataSourceByMysql.setUsername(username);
        druidDataSourceByMysql.setPassword(password);

        DruidDataSource druidDataSourceByOracle = new DruidDataSource();
        druidDataSourceByOracle.setDriverClassName(oracleDriver);
        druidDataSourceByOracle.setUrl(oracleUrl);
        druidDataSourceByOracle.setUsername(oracleUsername);
        druidDataSourceByOracle.setPassword(oraclePassword);

        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceMap.put("mysql", druidDataSourceByMysql);
        dataSourceMap.put("oracle", druidDataSourceByOracle);

        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(druidDataSourceByMysql);

        return dynamicDataSource;
    }
}
