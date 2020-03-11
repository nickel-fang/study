package com.jetsen.thread;

import java.util.concurrent.Semaphore;

/*
* 在 浅谈Java中的锁：Synchronized、重入锁、读写锁 一文中，我们了解了synch和读写锁，我们发现使用锁的时候一次只允许一条线程方法。
* 那么有什么东西可以提供更强大的控制方法么？这个东西就是信号量
*
//创建具有给定许可数的信号量
Semaphore(int permits):构造方法，创建
//拿走1个许可
void acquire()
//拿走多个许可
void acquire(int n)
//释放一个许可
void release()
//释放n个许可
void release(int n):
//当前可用的许可数
int availablePermits()
* */
public class SemaphoreDemo {
    public static Semaphore semaphore = new Semaphore(5);

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getId() + "号线程在" + System.currentTimeMillis() + "获取资源");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        public static void main(String[] args) {
            for (int i = 0; i < 30; i++) {
                new ThreadDemo().start();
            }
        }
    }
}
