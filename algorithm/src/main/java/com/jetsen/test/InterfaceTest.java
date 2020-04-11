package com.jetsen.test;

@FunctionalInterface
public interface InterfaceTest {
    void test1();

    default void test() {
        System.out.println("interface");
    }
}
