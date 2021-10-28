package lockAndThread.lockSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    private List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        LockSupportTest test = new LockSupportTest();

        t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                test.add(new Object());
                System.out.println("add " + i);
                if (test.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }

            System.out.println("t1结束");
        }, "t1");

        t2 = new Thread(() -> {
            System.out.println("t2启动");
            LockSupport.park();
            System.out.println("t2结束");

            LockSupport.unpark(t1);
        }, "t2");


        t2.start();
        t1.start();
    }

}
