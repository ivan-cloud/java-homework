package com.example.javahomework.w4_concurrence.t2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AsyncWay11_CyclicBarrier {
    private static int result = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
        System.out.println("barrierAction callback");
    });

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                result = sum();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        cyclicBarrier.await();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        cyclicBarrier.reset();
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
