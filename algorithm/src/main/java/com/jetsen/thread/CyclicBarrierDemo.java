package com.jetsen.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
* 相比较于CountDownLatch，CyclicBarrier可以完成前者的全部功能，但是相比前者，它的功能更加的强大。
CyclicBarrier翻译过来的中文名称叫循环栅栏，顾名思义它可以循环使用
CyclicBarrier还可以接收一个Runnable对象，当栅栏循环一次技术后会执行一次Runnable
*
//构造方法，第一个参数为栅栏饿长度，第二个就是上方所说的Runnable对象
public CyclicBarrier(int parties, Runnable barrierAction)
public CyclicBarrier(int parties)
//获取现在的数量
public int getParties()
//持续等待栅栏归零
public int await()
//最多等待unit时间单位内timeout时间
public int await(long timeout, TimeUnit unit)
**/
public class CyclicBarrierDemo {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new FinallyThreadDemo());

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " complete the task");
                cyclicBarrier.await();

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " complete the task");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class FinallyThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("while " + cyclicBarrier.getParties() + " tasks meet at the barrier, run this task!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new ThreadDemo().start();
        }
    }
}
