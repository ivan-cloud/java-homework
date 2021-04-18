package com.example.javahomework.w5_fx.t_ext_1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MyCacheAspect {
    static Object cacheObject = null;

    @Pointcut("execution(* com.example.javahomework.w5_fx.t_ext_1..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        MyCache myCache = method.getAnnotation(MyCache.class);
        if (myCache != null) {
            if (cacheObject == null) {
                System.out.println(">>>>>>> doesn't hit cache.");
                result = point.proceed();
                cacheObject = result;
                int timeout = myCache.value();
                Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                    System.out.println(">>>>>>> clear cache.");
                    cacheObject = null;
                }, timeout, TimeUnit.SECONDS);
            }
            result = cacheObject;
        } else {
            result = point.proceed();
        }
        System.out.println("return value: " + result);
        return result;
    }
}
