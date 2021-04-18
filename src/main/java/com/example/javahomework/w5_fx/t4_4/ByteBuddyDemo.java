package com.example.javahomework.w5_fx.t4_4;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        HelloService helloService = new ByteBuddy()
                .subclass(HelloService.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(LoggerAdvisor.class))
                .make()
                .load(HelloService.class.getClassLoader())
                .getLoaded()
                .newInstance();
        helloService.sayHello();
    }
}
