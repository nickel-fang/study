package com.jetsen.jdk8;

@FunctionalInterface
public interface MyNumber<T> {
    T getValue(T t);
}
