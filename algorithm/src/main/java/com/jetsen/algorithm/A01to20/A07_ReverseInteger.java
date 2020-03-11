package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A07_ReverseInteger {
    public static int reverseInteger(int x) {
        int cur = 0;
        int result = 0;
        while (x != 0) {
            cur = x % 10;
            x = x / 10;
            //check if result cause an overflow
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && cur > 7))
                return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && cur < -8))
                return 0;

            result = result * 10 + cur;
        }
        return result;
    }

    @Test
    public void testReverseInteger() {
        assertEquals(reverseInteger(123), 321);
        assertEquals(reverseInteger(-123), -321);
        assertEquals(reverseInteger(120), 21);
    }
}
