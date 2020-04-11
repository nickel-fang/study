package com.jetsen.web;

import com.jetsen.entity.Customer;
import com.jetsen.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NICKEL
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Value("${spring.redis.expire-time}")
    private int expireTime;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/set")
    public boolean redisSet(String key, String value) {
        Customer customer = new Customer(1, "Nickel", "Fang","测试");
        return redisUtil.set(key, customer, expireTime);
    }

    @RequestMapping("/message")
    public String message() {
        Customer customer = new Customer(1, "Nickel", "Fang","测试");
        redisUtil.convertAndSend("chat", customer);
        return "ok";
    }


    public Object redisGet(String key) {
        return redisUtil.get(key);
    }
}
