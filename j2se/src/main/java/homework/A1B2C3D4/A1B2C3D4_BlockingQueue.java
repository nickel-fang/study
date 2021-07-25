package homework.A1B2C3D4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class A1B2C3D4_BlockingQueue {
    static BlockingQueue<String> q1 = new LinkedBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new LinkedBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(() -> {
            for (char i = 65; i < 91; i++) {
                try {
                    q1.take();
                    System.out.print(i);
                    q2.put("OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    q1.put("OK");
                    q2.take();
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
