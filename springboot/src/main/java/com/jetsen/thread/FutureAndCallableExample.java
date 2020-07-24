package com.jetsen.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author: Nickel Fang
 * @date: 2020/7/10 11:50
 */
@Slf4j
public class FutureAndCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            log.info("proceed in the method to Callable");
            Thread.sleep(5000);
            return "Hello from Callable";
        };

        log.info("submit the callable into thread pool");
        Future<String> future=executorService.submit(callable);
        log.info("main thread continue to run");

        log.info("get the future value");
        while(!future.isDone()){
            log.info("callable is still running");
            Thread.sleep(1000);
        }
        String result = future.get();
        log.info("the value of future is {}",result);

        executorService.shutdown();
    }
}
