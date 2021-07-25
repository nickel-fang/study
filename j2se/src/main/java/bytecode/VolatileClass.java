package bytecode;

public class VolatileClass {
    int i;
    volatile int j;

    public synchronized void get() {
    }

    void n(){
        synchronized (this){

        }
    }
}
