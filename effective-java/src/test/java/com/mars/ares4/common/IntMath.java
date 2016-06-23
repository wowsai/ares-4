package com.mars.ares4.common;

public class IntMath {
    private IntMath() {
        throw new AssertionError();
    }

    public static Generator<Integer> fibonacci() {
        return new Fibonacci();
    }

    private static class Fibonacci implements Generator<Integer> {
        private TwoTuple<Integer, Integer> value = TwoTuple.create(0, 1);

        @Override
        public Integer next() {
            value = TwoTuple.create(value.second, value.first + value.second);
            return value.first;
        }
    }

    public static Generator<Integer> concurrentFibonacci() {
        return new ConcurrentFibonacci();
    }

    private static class ConcurrentFibonacci implements Generator<Integer> {
        private Fibonacci fibonacci = new Fibonacci();

        @Override
        public synchronized Integer next() {
            return fibonacci.next();
        }
    }
}
