package com.example.javahomework.w5_fx.t3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlLoadDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/w5_fx/t3/applicationContext.xml");
        Klass klass = applicationContext.getBean(Klass.class);
        System.out.println(klass);
    }
}
