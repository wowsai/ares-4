package com.mars.ares4.common;

import com.google.common.base.MoreObjects;

public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    private TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> TwoTuple<A, B> create(A first, B second) {
        return new TwoTuple<>(first, second);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("first", first)
                .add("second", second)
                .toString();
    }
}
