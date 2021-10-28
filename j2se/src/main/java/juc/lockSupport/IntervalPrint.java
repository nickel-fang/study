package lockAndThread.lockSupport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class IntervalPrint {
    static Thread t1 = null, t2 = null;
    static String[] str1 = {"a", "b", "c", "d", "e"};
    static String[] str2 = {"1", "2", "3", "4", "5"};

    static volatile boolean flag = false;

    static ReentrantLock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < str2.length; i++) {
                    System.out.println(str2[i]);
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c1.signal();
                }
            }finally {
                lock.unlock();
            }
        });
        t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < str1.length; i++) {
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(str1[i]);
                    c2.signal();

                }
            }finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }

//    public static void main(String[] args) {
//        t1 = new Thread(() -> {
//            for (int i = 0; i < str2.length; i++) {
//                System.out.println(str2[i]);
//                LockSupport.park();
//                LockSupport.unpark(t2);
//            }
//        });
//        t2 = new Thread(() -> {
//            for (int i = 0; i < str1.length; i++) {
//                LockSupport.unpark(t1);
//
//                System.out.println(str1[i]);
//                LockSupport.park();
//            }
//        });
//
//        t1.start();
//        t2.start();
//    }

/*    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < str2.length; i++) {
                while (flag) {
                    System.out.println(str2[i]);
                    flag = !flag;
                }
            }
        });
        t2 = new Thread(() -> {
            for (int i = 0; i < str1.length; i++) {
                while (!flag) {
                    System.out.println(str1[i]);
                    flag = !flag;
                }
            }
        });

        t1.start();
        t2.start();
    }*/


}
