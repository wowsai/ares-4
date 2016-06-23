package com.mars.ares4.common;

import java.util.concurrent.atomic.AtomicLong;

public class CountedObject {
    private static AtomicLong counter = new AtomicLong();
    private final long id = counter.getAndIncrement();

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject#" + id;
    }
}
