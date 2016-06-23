package com.mars.ares4.common;

import java.util.Random;

public class OtpGenerator implements Generator<String> {
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final Random random;
    private final int length;

    private OtpGenerator(long seed, int length) {
        this.random = new Random(seed);
        this.length = length;
    }

    public static Generator<String> create(long seed, int length) {
        return new OtpGenerator(seed, length);
    }

    @Override
    public String next() {
        char[] result = new char[length];
        for(int i = 0; i < length; i++) {
            result[i] = CHARS[random.nextInt(CHARS.length)];
        }
        return new String(result);
    }
}
