package com.example.javahomework.w4_concurrence.t2;

public class AsyncWay4_WaitNotify {

    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        AsyncThread asyncThread = new AsyncThread(object);
        asyncThread.start();

        synchronized (object) {
            object.wait();

            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + asyncThread.result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
            // 然后退出main线程
        }
    }

    public static class AsyncThread extends Thread {
        Object object;
        int result = 0;

        public AsyncThread(Object object) {
            this.object = object;
        }

        @Override
        public void run() {
            synchronized (object) {
                result = sum();
                object.notify();
            }
        }


        private int sum() {
            return fibo(36);
        }

        private int fibo(int a) {
            if (a < 2) {
                return 1;
            }
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
