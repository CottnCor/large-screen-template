package com.largeScreen.api.aop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = DataSourceEnum.ZXJZ)
    @ConfigurationProperties(prefix = "spring.datasource." + DataSourceEnum.ZXJZ)
    public DataSource dataSourceZxjz() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceEnum.ZNFW)
    @ConfigurationProperties(prefix = "spring.datasource." + DataSourceEnum.ZNFW)
    public DataSource dataSourceZnfw() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceZxjz());
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put(DataSourceEnum.ZXJZ, dataSourceZxjz());
        dsMap.put(DataSourceEnum.ZNFW, dataSourceZnfw());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
}
