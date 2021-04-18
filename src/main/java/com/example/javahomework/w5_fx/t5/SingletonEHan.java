package com.example.javahomework.w5_fx.t5;

/**
 * 饿汉式，从名字上也很好理解，就是“比较勤”，实例在初始化的时候就已经建好了，不管你有没有用到，都先建好了再说。
 * 好处是没有线程安全的问题，坏处是浪费内存空间。
 *
 * @author ivan
 */
public class SingletonEHan {
    private SingletonEHan() {
    }

    private static SingletonEHan instance = new SingletonEHan();

    public static SingletonEHan getInstance() {
        return instance;
    }
}
