package proxy.dynamicProxy.jdk;

public class ElectricCar implements Movable,Rechargeable {
    @Override
    public void move() {
        System.out.println("run quickly");
    }

    @Override
    public void recharge() {
        System.out.println("car is recharged");
    }

    public void horn(){
        System.out.println("car can horn");
    }
}
