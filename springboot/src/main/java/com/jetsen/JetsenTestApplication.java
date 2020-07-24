package com.jetsen;

import com.jetsen.service.AOPHelloworld;
import com.jetsen.service.impl.HelloWorldImpl1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: Nickel Fang
 * @date: 2020/7/6 14:39
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JetsenTestApplication {

    @Autowired
    private AOPHelloworld aopHelloworld;

    @Test
    public void testAOP() {
        aopHelloworld.printHelloWorld();
    }
}
