package com.jetsen.algorithm.other;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

public class A1195_FizzBuzzMultiThread2 {
    private int n;
    private int curNum;
    private AtomicInteger state;

    public A1195_FizzBuzzMultiThread2(int n) {
        this.n = n;
        this.state = new AtomicInteger(1);
        this.curNum = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            while (!state.compareAndSet(1, 0))
                LockSupport.parkNanos(1);
            if (curNum > n) {
                state.set(1);
                return;
            }
            if (curNum % 3 == 0 && curNum % 5 != 0) {
                printFizz.run();
                curNum++;
            }
            state.set(1);
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            while (!state.compareAndSet(1, 0))
                LockSupport.parkNanos(1);
            if (curNum > n) {
                state.set(1);
                return;
            }
            if (curNum % 3 != 0 && curNum % 5 == 0) {
                printBuzz.run();
                curNum++;
            }
            state.set(1);
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            while (!state.compareAndSet(1, 0))
                LockSupport.parkNanos(1);
            if (curNum > n) {
                state.set(1);
                return;
            }
            if (curNum % 3 == 0 && curNum % 5 == 0) {
                printFizzBuzz.run();
                curNum++;
            }
            state.set(1);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            while (!state.compareAndSet(1, 0))
                LockSupport.parkNanos(1);
            if (curNum > n) {
                state.set(1);
                return;
            }
            if (curNum % 3 != 0 && curNum % 5 != 0) {
                printNumber.accept(curNum);
                curNum++;
            }
            state.set(1);
        }
    }
}
