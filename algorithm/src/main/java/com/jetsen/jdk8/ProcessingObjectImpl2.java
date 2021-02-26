package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 10:00
 */
public class ProcessingObjectImpl2 extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
