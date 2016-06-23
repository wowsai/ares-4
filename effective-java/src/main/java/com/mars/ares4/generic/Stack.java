package com.mars.ares4.generic;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class Stack<E> {
    public static final int DEFAULT_CAPACITY = 10;
    private E[] values;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        Preconditions.checkArgument(capacity > 0, "argument capacity must great than zero.");
        this.capacity = capacity;
        values = (E[])new Object[capacity];
    }

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public void pushAll(Collection<? extends E> items) {
        for(E item : items) {
            push(item);
        }
    }

    public void push(E item) {
        ensureCapacity();
        values[size++] = item;
    }

    private void ensureCapacity() {
        if(size == capacity) {
            this.capacity *= 2;
            values = Arrays.copyOf(values, this.capacity);
        }
    }

    public int getSize() {
        return size;
    }

    public E pop() {
        if(size == 0) throw new EmptyStackException();
        E result = values[--size];
        values[size] = null;
        return result;
    }

    public void popAll(Collection<? super E> collection) {
        for(int i = 0; i < size; i++) {
            collection.add(pop());
        }
    }

    public static <E> Stack<E> newStack() {
        return new Stack<E>();
    }
}
