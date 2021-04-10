package com.example.javahomework.w4_concurrence.t2;

public class AsyncWay3_Syncronized {
    public static Integer result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (result) {
                    result = sum(); //这是得到的返回值}
                }
            }
        };
        new Thread(runnable).start();

        Thread.sleep(1);

        synchronized (result) {
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }
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
