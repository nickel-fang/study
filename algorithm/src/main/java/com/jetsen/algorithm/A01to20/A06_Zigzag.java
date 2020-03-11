package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A06_Zigzag {
    public static String zigzag(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int left = 0, right = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= s.length() / (2 * numRows - 2); j++) {
                left = (2 * numRows - 2) * j - i;
                right = (2 * numRows - 2) * j + i;
                if (left == right || (right - left) == (2 * numRows - 2)) {
                    if (left >= 0)
                        result.append(s.charAt(left));
                } else {
                    if (left > 0) {
                        result.append(s.charAt(left));
                    }
                    if (right < s.length()) {
                        result.append(s.charAt(right));
                    }
                }
            }
        }
        return result.toString();
    }

    public static String zigzag2(String s, int numRows) {

        if (numRows == 1 || numRows>=s.length()) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    @Test
    public void testZigzag() {
//        assertEquals(zigzag("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
//        assertEquals(zigzag("PAYPALISHIRING", 1), "PAYPALISHIRING");
        assertEquals(zigzag2("PAYPALISHIRING", 14), "PAYPALISHIRING");
        assertEquals(zigzag("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
    }
}
