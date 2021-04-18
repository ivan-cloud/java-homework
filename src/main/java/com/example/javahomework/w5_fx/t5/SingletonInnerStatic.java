package com.example.javahomework.w5_fx.t5;

/**
 * 静态内部类的方式效果类似双检锁，但实现更简单。但这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
 *
 * @author ivan
 */
public class SingletonInnerStatic {
    private SingletonInnerStatic() {

    }

    public static class SingletonHodler {
        public static final SingletonInnerStatic INSTANCE = new SingletonInnerStatic();
    }

    public static final SingletonInnerStatic getInstance() {
//        return SingletonHodler.INSTANCE;
        return null;
    }
}
