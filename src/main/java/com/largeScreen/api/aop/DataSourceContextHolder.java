package com.largeScreen.api.aop;

public class DataSourceContextHolder {

    public static final String DEFAULT_DS = DataSourceEnum.ZXJZ;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDB() {
        return (contextHolder.get());
    }

    public static void clearDB() {}
}
