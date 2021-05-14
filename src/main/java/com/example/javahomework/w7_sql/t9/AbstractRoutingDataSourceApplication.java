package com.example.javahomework.w7_sql.t9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
@MapperScan("com.example.javahomework.w7_sql.t9.dao")
public class AbstractRoutingDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AbstractRoutingDataSourceApplication.class);

        //设置environment中的profiler
        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("w7_sql.t9");

        application.setEnvironment(environment);
        application.run(args);
    }
}
