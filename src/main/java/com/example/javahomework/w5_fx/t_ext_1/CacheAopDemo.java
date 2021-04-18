package com.example.javahomework.w5_fx.t_ext_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CacheAopDemo {
    public static void main(String[] args) {
        SpringApplication.run(CacheAopDemo.class, args);
    }

    @Component
    public class MyApplicationRunner implements ApplicationRunner {
        @Autowired
        HelloService helloService;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            while (true) {
                Thread.sleep(1000);
                String result = helloService.sayHello();
            }
        }
    }
}
