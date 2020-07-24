package com.jetsen.service.impl;

import com.jetsen.aop.Time;
import com.jetsen.service.AOPHelloworld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Nickel Fang
 * @date: 2020/7/6 11:39
 */
@Slf4j
@Service
public class HelloWorldImpl1 implements AOPHelloworld {
    @Override
    @Time
    public void printHelloWorld() {
        log.info("------11111------按下HelloWorld1.printHelloWorld()-----11111111-------");
    }

    @Override
    public void doPrint() {
        System.out.println("------1111111------打印HelloWorldImpl1-----1111111------");
    }
}
