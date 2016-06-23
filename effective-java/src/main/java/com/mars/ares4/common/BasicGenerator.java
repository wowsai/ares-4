package com.mars.ares4.common;

public class BasicGenerator<T> implements Generator<T> {
    private final Class<T> type;

    private BasicGenerator(Class<T> type) {
        this.type = type;
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
