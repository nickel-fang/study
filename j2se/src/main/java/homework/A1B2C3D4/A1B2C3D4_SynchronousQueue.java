package homework.A1B2C3D4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class A1B2C3D4_SynchronousQueue {
    static BlockingQueue<String> q = new SynchronousQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (char i = 65; i < 91; i++) {
                try {
                    System.out.print(i);
                    q.put("OK");
                    q.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    q.take();
                    System.out.println(i);
                    q.put("OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
