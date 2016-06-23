package com.mars.ares4.generic;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackTest {
    @Test
    public void testPush() {
        Stack<Number> numberStack = Stack.newStack();
        numberStack.push(12);
        assertThat(numberStack.getSize()).isEqualTo(1);
        assertThat(numberStack.pop()).isEqualTo(12);
        assertThat(numberStack.getSize()).isEqualTo(0);
    }

    @Test
    public void testPushAll() {
        Stack<Number> numberStack = Stack.newStack();
        numberStack.pushAll(Lists.newArrayList(1,2.0,3,4,8));
        assertThat(numberStack.getSize()).isEqualTo(5);
        int size = numberStack.getSize();
        for(int i = 0; i < size; i++) {
            System.out.println(numberStack.pop());
        }
    }
}
