package com.mars.ares4.effective;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class BoxingTest {
    private static long slowAdd() {
        Long sum = 0L;
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i; // boxing, huge slow
        }
        return sum;
    }

    private static long add() {
        long sum = 0L;
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    @Test
    public void testLongAdd() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long slowResult = slowAdd();
        System.out.println("result is: " + slowResult + " and cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        stopwatch.reset().start();
        long result = add();
        System.out.println("result is: " + result + " and cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
