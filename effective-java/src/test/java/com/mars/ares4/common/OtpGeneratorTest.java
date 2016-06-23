package com.mars.ares4.common;

import org.junit.Test;

public class OtpGeneratorTest {
    @Test
    public void otp() {
        long seed = System.nanoTime();
        Generator<String> otp1 = OtpGenerator.create(seed, 6);
        Generator<String> otp2 = OtpGenerator.create(seed, 6);

        for(int i = 0; i < 6; i++) {
            System.out.println(otp1.next() + " " + otp2.next());
        }
    }
}
