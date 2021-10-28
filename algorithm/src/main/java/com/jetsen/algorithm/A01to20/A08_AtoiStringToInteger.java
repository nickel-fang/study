package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A08_AtoiStringToInteger {
    public static int atoi(String s) {
        int result = 0;
        boolean isMinus = false;
        boolean isHasSign = false;
        boolean isStartWithNumber = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {  //numerical digit
                if (!isMinus && (result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && c - '0' > 7))
                    return Integer.MAX_VALUE;
                if (isMinus && (-result < Integer.MIN_VALUE / 10 || -result == Integer.MIN_VALUE / 10 && -(c - '0') < -8))
                    return Integer.MIN_VALUE;
                result = result * 10 + (c - '0');
                isStartWithNumber = true;
            } else if (!isStartWithNumber && c == ' ' && !isHasSign) { //空格
                continue;
            } else if (c == '-' && !isStartWithNumber && !isHasSign) { // 负号
                isMinus = true;
                isHasSign = true;
            } else if (c == '+' && !isStartWithNumber && !isHasSign) { //正号
                isHasSign = true;
            } else if (result == 0 && c != '-' && c == '+' && (c < '0' || c > '9')) {
                return 0;
            } else {
                break;
            }
        }
        return isMinus ? -result : result;
    }

    @Test
    public void testAtoi() {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        assertEquals(atoi("0-1"), 0);
        assertEquals(atoi("-2147483648"), -2147483648);
        assertEquals(atoi("  -42"), -42);
        assertEquals(atoi("  --42"), 0);
        assertEquals(atoi("  "), 0);
        assertEquals(atoi("4193 with words"), 4193);
        assertEquals(atoi("41-93 with words"), 41);
        assertEquals(atoi("words and 987"), 0);
        assertEquals(atoi("-91283472332"), -2147483648);
    }
}
