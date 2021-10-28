package homework.A1B2C3D4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class A1B2C3D4_ReentrantLock {
    static ReentrantLock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (char i = 65; i < 91; i++) {
                    System.out.print(i);
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i < 27; i++) {
                    System.out.println(i);
                    c1.signal();
                    c2.await();
                }
//                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });

        t1.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
