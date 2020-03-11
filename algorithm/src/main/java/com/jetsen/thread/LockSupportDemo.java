package com.jetsen.thread;

import java.util.concurrent.locks.LockSupport;

/*
* wait和notify实现了线程之间的协作，其实关于线程协作JDK还为我们提供了另外一个工具类LockSupport。
*
// 禁用当前线程
static void park()
// 如果参数线程的不可用，则使其可用。
static void unpark(Thread thread)
* */
public class LockSupportDemo {
    public static Thread thread;

    static class WaitThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("WaitThread wait,time=" + System.currentTimeMillis());
            thread = Thread.currentThread();
            LockSupport.park();
            System.out.println("WaitThread end,time=" + System.currentTimeMillis());
        }
    }

    static class NotifyThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("NotifyThread notify,time=" + System.currentTimeMillis());
            LockSupport.unpark(thread);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NotifyThread end,time=" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        WaitThreadDemo waitThreadDemo = new WaitThreadDemo();
        NotifyThreadDemo notifyThreadDemo = new NotifyThreadDemo();
        waitThreadDemo.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyThreadDemo.start();
    }
}
