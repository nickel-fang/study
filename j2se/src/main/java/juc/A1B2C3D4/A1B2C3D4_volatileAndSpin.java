package homework.A1B2C3D4;

public class A1B2C3D4_volatileAndSpin {
    static volatile boolean flag = true;


    public static void main(String[] args) {
        new Thread(() -> {
            for (char i = 65; i < 91; i++) {
                while (!flag) {

                }
                System.out.print(i);
                flag = !flag;
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (flag) {

                }
                System.out.println(i);
                flag = !flag;
            }
        }).start();
    }
}
