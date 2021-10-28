package homework.A1B2C3D4;

import java.util.concurrent.Semaphore;

public class A1B2C3D4_Semaphore {
    static Semaphore s1 = new Semaphore(0);
    static Semaphore s2 = new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (char i = 65; i < 91; i++) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                s2.release();
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    s1.release();
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);

            }
        }).start();
    }
}
