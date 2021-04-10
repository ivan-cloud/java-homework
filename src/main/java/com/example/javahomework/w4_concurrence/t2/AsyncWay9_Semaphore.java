package com.example.javahomework.w4_concurrence.t2;

import java.util.concurrent.Semaphore;

public class AsyncWay9_Semaphore {
    private static int result = 0;
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquireUninterruptibly();
                    result = sum();
                } finally {
                    semaphore.release();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(1);
        try {
            semaphore.acquireUninterruptibly();
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

            // 然后退出main线程
        } finally {
            semaphore.release();
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
