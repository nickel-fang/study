package com.jetsen.test;

import org.junit.jupiter.api.Test;

public class InterfaceImpl implements InterfaceTest {
    @Override
    public void test1() {

    }

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Override
    public void test(){
        InterfaceTest.super.test();
        System.out.println("interfaceImpl");
    }

    @Test
    public void testFunction() {
        InterfaceImpl i = new InterfaceImpl();
        i.test();
    }
}
