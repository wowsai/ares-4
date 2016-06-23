package com.mars.ares4.math;

public class Hanoi {
    public static void hanoi(int n, char a, char b, char c) {
        if(n == 0) return;
        hanoi(n-1, a, c, b);
        System.out.printf("%c->%c\n", a, b);
        hanoi(n-1, c, b, a);
    }

    public static void main(String[] args) {
        hanoi(12, 'A', 'B', 'C');
    }
}
