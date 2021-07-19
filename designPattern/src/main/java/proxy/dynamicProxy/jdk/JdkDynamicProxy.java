package proxy.dynamicProxy.jdk;

import java.lang.reflect.Proxy;

public class JdkDynamicProxy {

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    private Object proxy;

    public JdkDynamicProxy(ElectricCar car) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); //设置系统属性，将生成的class文件保存成文件
        this.proxy = Proxy.newProxyInstance(ElectricCar.class.getClassLoader(), ElectricCar.class.getInterfaces(), (proxy, method, args) -> {
            System.out.println(proxy.getClass().getName());
            Object obj = method.invoke(car, args);
            System.out.println(obj);
            return null;
        });
    }

    public static void main(String[] args) {
        Object proxy = new JdkDynamicProxy(new ElectricCar()).getProxy();
        ((Movable) proxy).move();
        ((Rechargeable) proxy).recharge();
    }
}
