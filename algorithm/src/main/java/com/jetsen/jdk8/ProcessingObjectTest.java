package com.jetsen.jdk8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author: Nickel Fang
 * @date: 2020/12/4 10:01
 */
public class ProcessingObjectTest {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new ProcessingObjectImpl1();
        ProcessingObject<String> p2 = new ProcessingObjectImpl2();
        ProcessingObject<String> p3 = new ProcessingObjectImpl3();
        p1.setSuccessor(p2);
        p2.setSuccessor(p3);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);


        UnaryOperator<String> p4 = text -> "From Nickel: " + text;
        UnaryOperator<String> p5 = text -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = p4.andThen(p5);
        result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }
}
