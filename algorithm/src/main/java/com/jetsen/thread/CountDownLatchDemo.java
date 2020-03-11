package com.jetsen.thread;

import java.util.concurrent.CountDownLatch;

/*CountDownLatch是JDK为我们提供的一个计数器，它的操作是原子操作，同一时间只能有一个线程去操作这个它。
*
//构造方法，接收计数器的数量
public CountDownLatch(int count)
//持续等待计数器归零
public void await()
//最多等待unit时间单位内timeout时间
public boolean await(long timeout, TimeUnit unit)
//计数器减1
public void countDown()
//返回现在的计数器数量
public long getCount()
* */
public class CountDownLatchDemo {
    public static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class ThreadDemo implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + " complete the task");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo()).start();
        }
        countDownLatch.await();
        System.out.println("all the task have been completed");
    }
}
