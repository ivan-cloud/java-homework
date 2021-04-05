
package com.example.javahomework.w3_concurrence.t5.conc0302.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount {

    private AtomicInteger num = new AtomicInteger();

    public int add() {
        return num.getAndIncrement();
    }

    public int getNum() {
        return num.get();
    }
}
