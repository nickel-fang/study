package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A09_PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        //将整数反转
        int result = 0, cur = 0;
        int y = x;

        while (y > 0) {
            cur = y % 10;
            y = y / 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && cur > 7))
                return false;
            result = result * 10 + cur;
        }
        if (result == x)
            return true;
        return false;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 10 && x >= 0)
            return true;
        if (x < 0 || x % 10 == 0)
            return false;

        int result = 0;
        while (result < x) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return x == result || x == result / 10;
    }

    @Test
    public void testIsPalindrome() {
        assertEquals(isPalindrome2(1221), true);
        assertEquals(isPalindrome2(121), true);
        assertEquals(isPalindrome2(-121), false);
        assertEquals(isPalindrome2(10), false);
        assertEquals(isPalindrome2(0), true);
    }
}
