package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 17:41
 */
public class C implements A, B {
    public static void main(String[] args) {
        new C().hello();
    }

    @Override
    public void hello() {
        B.super.hello();
    }
}
