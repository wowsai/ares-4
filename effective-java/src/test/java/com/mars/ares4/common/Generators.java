package com.mars.ares4.common;

import java.util.Collection;

public class Generators {
    private Generators() {
        throw new AssertionError();
    }

    public static <T> Collection<T> fill(Collection<T> coll, Generator<? extends T> gen, int n) {
        for(int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }
}
