package com.mars.ares4.common;

import org.junit.Test;

public class BasicGeneratorTest {
    @Test
    public void testBasicGenerator() {
        Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
        for(int i = 0; i < 100; i++) {
            System.out.println(gen.next());
        }
    }
}
