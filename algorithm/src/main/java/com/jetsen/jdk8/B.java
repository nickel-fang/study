package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 17:41
 */
public interface B {
    default void hello(){
        System.out.println("hello from b");
    }
}
