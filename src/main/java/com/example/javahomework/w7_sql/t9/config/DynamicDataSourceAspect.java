package com.example.javahomework.w7_sql.t9.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(com.example.javahomework.w7_sql.t9.config.ReadOnly)")
    public void beforeSwitchDataSource(JoinPoint joinPoint) {
        DataSourceContextHolder.setDataSource(DataSourceConfig.SLAVE_DATASOURCE);
    }

    @After("@annotation(com.example.javahomework.w7_sql.t9.config.ReadOnly)")
    public void afterSwitchDataSource(JoinPoint joinPoint) {
        DataSourceContextHolder.setDataSource(DataSourceConfig.MASTER_DATASOURCE);
    }
}
