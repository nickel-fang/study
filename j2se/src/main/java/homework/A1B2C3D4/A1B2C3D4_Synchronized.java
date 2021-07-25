package homework.A1B2C3D4;

public class A1B2C3D4_Synchronized {
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                for (char i = 65; i < 91; i++) {
                    System.out.print(i);
                    try {
                        o.wait();
                        o.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                for (int i = 1; i < 27; i++) {
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        Thread.sleep(1);  //保证t1先于t2执行
        t2.start();
    }
}
