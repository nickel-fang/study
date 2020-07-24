package com.jetsen.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author: Nickel Fang
 * @date: 2020/7/10 15:30
 */
@Slf4j
public class MakeTeaExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<String> ft1 = new FutureTask<String>(new T1Task());
        FutureTask<String> ft2 = new FutureTask<String>(new T2Task());

        executorService.submit(ft1);
        executorService.submit(ft2);

        log.info(ft1.get() + ft2.get());
        log.info("start to brew");

        executorService.shutdown();
    }


    private static class T2Task implements java.util.concurrent.Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);

            return "T1:开水已备好";
        }
    }

    private static class T1Task implements java.util.concurrent.Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            log.info("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "T2:福鼎白茶拿到了";
        }
    }
}
