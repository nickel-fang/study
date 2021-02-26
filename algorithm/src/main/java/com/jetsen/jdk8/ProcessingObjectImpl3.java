package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 10:03
 */
public class ProcessingObjectImpl3 extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input + " end!";
    }
}
