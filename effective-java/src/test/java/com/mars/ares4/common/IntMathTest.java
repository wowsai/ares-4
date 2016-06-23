package com.mars.ares4.common;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntMathTest {
    @Test
    public void testFibonacci() throws InterruptedException {
        final Generator<Integer> fibonacci = IntMath.concurrentFibonacci();
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1000);
        for(int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.print(fibonacci.next() + " ");
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}
