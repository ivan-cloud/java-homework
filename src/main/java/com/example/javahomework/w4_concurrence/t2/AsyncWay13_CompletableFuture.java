package com.example.javahomework.w4_concurrence.t2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncWay13_CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> sum());

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + completableFuture.join());

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
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
