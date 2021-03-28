package com.example.javahomework.w1_jvm.t1;

// w1_jvm.(可选)自己写一个简单的 Hello.java，里面需要涉及基本类型、四则运行、if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
public class Hello {
    public static void main(String[] args) {
        byte b = 1;
        int i = 2;
        long l = 3;
        double d = 4;
        String s = "a";

        // 四则运算
        i = i + b;
        l = l * i;
        d = l - d;
        d = d / i;
        s = s + "b";

        // if
        if (d > 0) {
            System.out.println("d: " + d);
        }
        // for
        for (i = 0; i < d; i++) {
            System.out.println("i: " + i);
        }
    }
}