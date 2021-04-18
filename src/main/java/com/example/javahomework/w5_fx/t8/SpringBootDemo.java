package com.example.javahomework.w5_fx.t8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemo {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootDemo.class, args);
        Klass klass = context.getBean(Klass.class);
        System.out.println(klass);
    }
}
