package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A13_RomanToInt {
    public static int romanToInt(String roman) {
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] chars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int result = 0;

        while (!roman.isEmpty()) {
            for (int i = 0; i < ints.length; ) {
                if (roman.startsWith(chars[i])) {
                    result += ints[i];
                    roman = roman.substring(chars[i].length());
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    @Test
    public void testromanToInt() {
        assertEquals(romanToInt("III"), 3);
        assertEquals(romanToInt("IV"), 4);
        assertEquals(romanToInt("IX"), 9);
        assertEquals(romanToInt("LVIII"), 58);
        assertEquals(romanToInt("MCMXCIV"), 1994);
    }
}
