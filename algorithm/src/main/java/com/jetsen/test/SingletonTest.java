package com.jetsen.test;

import java.util.concurrent.CountDownLatch;

public class SingletonTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch count = new CountDownLatch(100000);
        for(int i=0;i<100000;i++){
            new Thread(){
                public void run() {
                    Singleton.getInstance();
                    count.countDown();
                }
            }.start();
        }

        count.await();
        System.out.println(System.currentTimeMillis()-start);
    }
}
