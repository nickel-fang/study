package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A38_CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        return netCountAndSay(countAndSay(n - 1));
    }

    private String netCountAndSay(String str) {
        StringBuilder result = new StringBuilder();
        int left = 0, right = 0;
        while (left < str.length()) {
            char leftChar = str.charAt(left);
            right = left + 1;
            if (right == str.length()) {
                result.append("1").append(leftChar);
                break;
            } else {
                char rightChar = str.charAt(right);
                while (rightChar == leftChar && ++right < str.length()) {
                    rightChar = str.charAt(right);
                }
                result.append(right - left).append(leftChar);
                left = right;
            }
        }
        return result.toString();
    }

    @Test
    public void testCountAndSay() {
        assertEquals(countAndSay(4), "1211");
        assertEquals(countAndSay(5), "111221");
    }
}
