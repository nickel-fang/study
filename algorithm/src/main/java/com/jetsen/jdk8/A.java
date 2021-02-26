package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 17:39
 */
public interface A {
    default void hello() {
        System.out.println("hello from a");
    }
}
