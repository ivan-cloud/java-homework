package com.example.javahomework.w7_sql.t9.config;

import org.springframework.util.StringUtils;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        System.out.println("切换到{}数据源:" + dataSource);
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        String result = contextHolder.get();
        if (!StringUtils.hasText(result)) {
            result = DataSourceConfig.MASTER_DATASOURCE;
        }
        return result;
    }

    public static void clear() {
        contextHolder.remove();
    }
}
