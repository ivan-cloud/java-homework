package com.example.javahomework.w5_fx.t2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JavaConfig {
    @Bean
    @Primary
    public HelloBean helloBean() {
        return new HelloBean2();
    }

    public HelloBean helloBean4() {
        return new HelloBean4();
    }
}
