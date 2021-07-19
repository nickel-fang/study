package proxy.dynamicProxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class cglibDynamicProxy {

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    private Object proxy;

    public cglibDynamicProxy(ElectricCar car) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(car.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("call the method");
                proxy.invokeSuper(obj, args);
                return null;
            }
        });

        this.proxy = enhancer.create();

    }

    public static void main(String[] args) {
        Object proxy = new cglibDynamicProxy(new ElectricCar()).getProxy();
        ((ElectricCar) proxy).move();
    }
}
