package com.example.javahomework.w5_fx.t2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource(locations = {"classpath:/w5_fx/t2/applicationContext.xml"})
public class BeanLoadDemo {

    public static void main(String[] args) {
        // 1. load from xml
        ApplicationContext applicationContext = SpringApplication.run(BeanLoadDemo.class, args);
        HelloBean helloBean1 = (HelloBean) applicationContext.getBean("helloBean1");
        helloBean1.say("3. load from xml by ImportResource");

        ApplicationContext context = new ClassPathXmlApplicationContext("/w5_fx/t2/applicationContext.xml");
        helloBean1 = (HelloBean) context.getBean("helloBean1");
        helloBean1.say("4. load from xml by ClassPathXmlApplicationContext");

    }

    @Component
    public class MyApplicationRunner implements ApplicationRunner {
        @Autowired
        HelloBean helloBean2;

        @Resource(name = "helloBean3")
        HelloBean helloBean3;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            // 2. load from java config
            helloBean2.say("1. load from JavaConfig");

            helloBean3.say("2. load from @Component");
        }
    }
}
