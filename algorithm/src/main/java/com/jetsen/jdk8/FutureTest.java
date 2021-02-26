package com.jetsen.jdk8;

import java.util.concurrent.*;

/**
 * @author: Nickel Fang
 * @date: 2020/12/8 16:44
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        //    ExecutorService executor = new ThreadPoolExecutor()
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() ->{
            Thread.sleep(2000);
            return new Double(1);
        });

        System.out.println("main thread");
        Double result = future.get(2, TimeUnit.SECONDS);
        System.out.println(result);
    }


}
