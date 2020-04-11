package com.jetsen.task;

import com.jetsen.service.impl.RedisReceiver;
import com.jetsen.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author NICKEL
 */
@Slf4j
//@Configuration
public class SendingRedisData {
    @Autowired
    private RedisReceiver redisReceiver;
    @Autowired
//    private RedisUtil redisUtil;
    private RedisTemplate redisTemplate;

    @Bean(value = "commandLineRunnerOfSendingRedisData")
    public CommandLineRunner run() throws Exception {
        return args -> {
            while (redisReceiver.getCount() == 0) {
//                log.info("sending message");
//                redisUtil.set("chat", "Hello redis!", 100);
//                redisTemplate.convertAndSend("chat", "Hello from Redis!");
                Thread.sleep(5000);
            }
        };
    }
}
