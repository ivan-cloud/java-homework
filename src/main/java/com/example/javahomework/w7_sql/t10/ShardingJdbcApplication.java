package com.example.javahomework.w7_sql.t10;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
@MapperScan("com.example.javahomework.w7_sql.t10.dao")
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShardingJdbcApplication.class);

        //设置environment中的profiler
        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("w7_sql.t10");

        application.setEnvironment(environment);
        application.run(args);
    }
}
