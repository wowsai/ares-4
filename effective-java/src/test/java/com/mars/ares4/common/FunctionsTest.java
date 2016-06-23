package com.mars.ares4.common;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FunctionsTest {
    @Test
    public void testIdentityFunction() {
        String[] strings = { "Jute", "Hemp", "Nylon" };
        UnaryFunction<String> sameString = Functions.identityFunction();
        for(String s : strings) {
            Assertions.assertThat(s == sameString.apply(s));
        }

        Number[] numbers = { 1, 2.0, 3L, 4.0f };
        UnaryFunction<Number> sameNumber = Functions.identityFunction();
        for(Number number : numbers) {
            Assertions.assertThat(number == sameNumber.apply(number));
        }
    }
}
