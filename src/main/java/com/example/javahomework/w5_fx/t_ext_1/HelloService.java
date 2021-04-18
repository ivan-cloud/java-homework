package com.example.javahomework.w5_fx.t_ext_1;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @MyCache(10)
    public String sayHello() {
        String value = "hello, aop";
        System.out.println("invoke from non-cached.");
        return value;
    }
}
