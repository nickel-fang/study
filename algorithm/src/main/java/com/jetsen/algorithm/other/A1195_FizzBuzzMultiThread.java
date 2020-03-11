package com.jetsen.algorithm.other;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class A1195_FizzBuzzMultiThread {
    private int n;
    private Semaphore fizzS;
    private Semaphore buzzS;
    private Semaphore fizzBuzzS;
    private Semaphore numberS;

    public A1195_FizzBuzzMultiThread(int n) {
        this.n = n;
        fizzS = new Semaphore(0);
        buzzS = new Semaphore(0);
        fizzBuzzS = new Semaphore(0);
        numberS = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= (n / 3 - n / 15); i++) {
            fizzS.acquire();
            printFizz.run();
            numberS.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= (n / 5 - n / 15); i++) {
            buzzS.acquire();
            printBuzz.run();
            numberS.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n / 15; i++) {
            fizzBuzzS.acquire();
            printFizzBuzz.run();
            numberS.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            numberS.acquire();
            if (num % 15 == 0) {
                fizzBuzzS.release();
            } else if (num % 3 == 0) {
                fizzS.release();
            } else if (num % 5 == 0) {
                buzzS.release();
            } else {
                printNumber.accept(num);
                numberS.release();
            }
        }
    }
}
