package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A397_IntegerReplacement {
    public int integerReplacement2(int n) {
        int result = 0;
        while (n != 1) {
            if ((n & 1) == 0)
                n >>>= 1;
            else if (n == 3 || (n >>> 1 & 1) == 0)
                n--;
            else
                n++;
            result++;
        }
        return result;
    }

    public int integerReplacement(int n) {
        int[] replaces = new int[n + 1];
        replaces[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0)
                replaces[i] = 1 + replaces[i / 2];
            else replaces[i] = Math.min(1 + replaces[i - 1], 2 + replaces[(i + 1) / 2]);
        }
        return replaces[n];
    }

    @Test
    public void testIntegerReplacement() {
        assertEquals(integerReplacement2(2147483647), 31);
        assertEquals(integerReplacement(7), 4);
    }
}
