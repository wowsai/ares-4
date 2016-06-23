package com.mars.ares4.common;

public class Tuple {
    private Tuple() {
        throw new AssertionError();
    }

    public static <A, B> TwoTuple<A, B> tuple(A first, B second) {
        return new TwoTuple<>(first, second);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A first, B second, C third) {
        return new ThreeTuple<>(first, second, third);
    }

    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A first, B second, C third, D fourth) {
        return new FourTuple<>(first, second, third, fourth);
    }

    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A first, B second, C third, D fourth, E fifth) {
        return new FiveTuple<>(first, second, third, fourth, fifth);
    }

    public static class TwoTuple<A, B> {
        public final A first;
        public final B second;

        private TwoTuple(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
        public final C third;

        private ThreeTuple(A first, B second, C third) {
            super(first, second);
            this.third = third;
        }
    }

    public static class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
        public final D fourth;

        private FourTuple(A first, B second, C third, D fourth) {
            super(first, second, third);
            this.fourth = fourth;
        }
    }

    public static class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
        public final E fifth;

        private FiveTuple(A first, B second, C third, D fourth, E fifth) {
            super(first, second, third, fourth);
            this.fifth = fifth;
        }
    }
}
