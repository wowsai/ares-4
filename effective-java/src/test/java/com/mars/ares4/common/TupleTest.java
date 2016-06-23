package com.mars.ares4.common;

import org.junit.Test;

public class TupleTest {
    @Test
    public void testTuple() {
        System.out.println(Tuple.tuple(1L, 2.0, "Test").third);
    }
}
