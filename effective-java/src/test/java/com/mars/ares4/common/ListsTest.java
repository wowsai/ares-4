package com.mars.ares4.common;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ListsTest {
    @Test
    public void asList() {
        List<Number> numbers = Lists.asList(BigDecimal.ONE, 0xcafebabeL, 4f, 5L, 6.0d, BigInteger.TEN);
        System.out.println(Joiner.on(", ").join(Collections2.transform(numbers, new Function<Number, String>() {
            @Override
            public String apply(Number input) {
                return input.getClass().getSimpleName();
            }
        })));
        List<Integer> integers = Lists.asList(1, 4, 6, 8, 9);
        System.out.println(numbers);
        System.out.println(integers);
    }
}
