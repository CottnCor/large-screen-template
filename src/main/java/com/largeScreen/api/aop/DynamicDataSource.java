package com.largeScreen.api.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("切换到数据源：" + DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}