package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A29_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        boolean isMinus = (dividend ^ divisor) < 0; //是否为负数
        int minusDividend = dividend > 0 ? -dividend : dividend;
        int minusDivisor = divisor > 0 ? -divisor : divisor;
        if (minusDividend > minusDivisor) return 0;

        int result = divideMinus(minusDividend, minusDivisor);

        if (result == Integer.MIN_VALUE && !isMinus)
            return Integer.MAX_VALUE;
        return isMinus ? result : -result;
    }

    private int divideMinus(int minusDividend, int minusDivisor) {
        if (minusDividend > minusDivisor) return 0;
        int result = -1, tmpDivisor = minusDivisor;
        while ((tmpDivisor << 1) >= minusDividend) {
            if (tmpDivisor <= (Integer.MIN_VALUE >> 1)) break;
            tmpDivisor = tmpDivisor << 1;
            result = result << 1;
        }
        return result + divideMinus(minusDividend - tmpDivisor, minusDivisor);
    }

    public int divide1(int dividend, int divisor) {
        boolean isMinus = (dividend ^ divisor) < 0; //是否为负数
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);
        if (lDividend < lDivisor) return 0;

        long lResult = divide1(lDividend, lDivisor);
        if (lResult > Integer.MAX_VALUE)
            return isMinus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        else
            return isMinus ? -(int) lResult : (int) lResult;
    }

    private long divide1(long lDividend, long lDivisor) {
        if (lDividend < lDivisor) return 0;
        long result = 1, tmpDivisor = lDivisor;
        while ((tmpDivisor << 1) <= lDividend) {
            tmpDivisor = tmpDivisor << 1;
            result = result << 1;
        }
        return result + divide1(lDividend - tmpDivisor, lDivisor);
    }

    @Test
    public void testDivide() {
        assertEquals(divide(Integer.MIN_VALUE, -1), Integer.MAX_VALUE);
        assertEquals(divide(-2147483648, -3), 715827882);

        assertEquals(divide(7, -3), -2);
    }
}
