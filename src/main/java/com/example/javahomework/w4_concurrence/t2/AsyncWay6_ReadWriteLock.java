package com.example.javahomework.w4_concurrence.t2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AsyncWay6_ReadWriteLock {
    private static int result = 0;
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.writeLock().lock();
                    System.out.println("writeLock.lock");
                    result = sum();
                } finally {
                    readWriteLock.writeLock().unlock();
                    System.out.println("writeLock.unlock");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(1);
        try {
            readWriteLock.readLock().lock();
            System.out.println("readLock.lock");
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

            // 然后退出main线程
        } finally {
            readWriteLock.readLock().unlock();
            System.out.println("readLock.unlock");
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
