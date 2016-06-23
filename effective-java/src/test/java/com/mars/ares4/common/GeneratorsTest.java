package com.mars.ares4.common;

import org.junit.Test;

public class GeneratorsTest {
    @Test
    public void testFill() {
        System.out.println(
                Generators.fill(
                        com.google.common.collect.Lists.newArrayList(),
                        IntMath.fibonacci(),
                        20
                )
        );
    }
}
