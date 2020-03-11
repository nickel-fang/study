package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long start = 2, end = num / 2, middle = 0;
        while (start <= end) {
            middle = (start + end) / 2;
            if ((middle * middle) == num) return true;
            else if ((middle * middle) < num) start = middle + 1;
            else end = middle - 1;
        }
        return false;
    }

    public boolean isPerfectSquare2(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0 ? true : false;

    }

    @Test
    public void testIsPerfectSquare() {
        assertEquals(isPerfectSquare(808201), true);
    }
}
