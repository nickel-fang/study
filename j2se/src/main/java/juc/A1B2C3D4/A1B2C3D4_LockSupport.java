package homework.A1B2C3D4;

import java.util.concurrent.locks.LockSupport;

public class A1B2C3D4_LockSupport {
    static Thread t1, t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (char i = 65; i < 91; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
