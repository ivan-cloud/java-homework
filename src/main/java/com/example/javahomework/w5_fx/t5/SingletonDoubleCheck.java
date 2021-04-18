package com.example.javahomework.w5_fx.t5;

/**
 * 双检锁，又叫双重校验锁，综合了懒汉式和饿汉式两者的优缺点整合而成。
 * 看上面代码实现中，特点是在synchronized关键字内外都加了一层 if 条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。
 *
 * @author ivan
 */
public class SingletonDoubleCheck {
    private SingletonDoubleCheck() {
    }

    private static SingletonDoubleCheck instance;

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
                return instance;
            }
        }
        return instance;
    }
}
