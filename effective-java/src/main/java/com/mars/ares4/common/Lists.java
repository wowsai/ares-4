package com.mars.ares4.common;

import java.io.Serializable;
import java.util.*;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

public class Lists {
    private Lists() {
        throw new AssertionError();
    }

    public static <E, T extends E> List<E> asList(T first, T... rest) {
        return new OnePlusArrayList<E>(first, rest);
    }

    private static final class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        final E first;
        final E[] rest;

        OnePlusArrayList(E first, E[] rest) {
            this.first = first;
            this.rest = checkNotNull(rest);
        }

        @Override
        public E get(int index) {
            checkElementIndex(index, size());
            return (index == 0) ? first : rest[index - 1];
        }

        @Override
        public int size() {
            return rest.length + 1;
        }
    }

    public static <T extends Comparable<T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if(t.compareTo(result) > 0) {
                result = t;
            }
        }
        return result;
    }
}
