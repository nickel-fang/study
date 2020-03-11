package com.jetsen.test;

public class Singleton {
    public static volatile Singleton singleton;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton = new Singleton();
                    System.out.println("init");
                }
            }
        }
        return  singleton;
    }
}
