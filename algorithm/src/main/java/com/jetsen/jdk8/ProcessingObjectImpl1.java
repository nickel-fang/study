package com.jetsen.jdk8;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 9:57
 */
public class ProcessingObjectImpl1 extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From Nickel: " + input;
    }
}
