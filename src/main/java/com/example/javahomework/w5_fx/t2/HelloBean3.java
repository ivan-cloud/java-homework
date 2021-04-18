package com.example.javahomework.w5_fx.t2;

import org.springframework.stereotype.Component;

@Component
public class HelloBean3 implements HelloBean {

    @Override
    public void say(String value) {
        System.out.println("HelloBean3: " + value);
    }
}