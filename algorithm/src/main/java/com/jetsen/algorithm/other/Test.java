package com.jetsen.algorithm.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    public int maxNumber(int[] A) {
        int num[] = new int[A.length];

        num[0] = A[0];
        num[1] = Math.max(A[0], A[1]);
        for (int i = 2; i < num.length; i++) {
            num[i] = Math.max(num[i - 1], num[i - 2] + A[i]);
        }
        return num[num.length - 1];
    }


    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(maxNumber(new int[]{1, 2, 4, 1, 7, 8, 3}), 15);
        assertEquals(maxNumber(new int[]{4,1,1,9,1}), 13);
    }

}
