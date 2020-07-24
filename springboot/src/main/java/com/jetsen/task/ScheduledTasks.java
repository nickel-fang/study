package com.jetsen.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NICKEL
 * 默认是单线程同步跑多个@Scheduled注解的任务。
 * @EnableAsync，开启异步操作，存在同一种任务(task1)并行执行，引发数据不一致风险
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

/*    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }*/

    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.error("我是task1111，我需要执行 10s 钟的时间，我的线程的 id == > {}，时间 == >{}", Thread.currentThread().getId(), new Date());
        Thread.sleep(10000);
        log.error("task1111 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }
    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        log.error("我是task2222，我需要执行 2s 钟的时间，我的线程的 id == > {}，时间 == >{}", Thread.currentThread().getId(), new Date());
        Thread.sleep(2000);
        log.error("task2222 ending ,我的线程的 id == > {} , 时间 == > {}", Thread.currentThread().getId(), new Date());
    }
}
