package com.example.javahomework.w5_fx.t4_4;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

public class LoggerAdvisor {
    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println("Enter " + method.getName());
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println("Exit " + method.getName());
        }
    }
}
