package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A12_IntegerToRoman {
    /*Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000*/
    public static String intToRoman(int num) {
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] chars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        for (int i = 0; i < ints.length; i++) {
            tmp = num / ints[i];
            num = num - tmp * ints[i];
            for (int j = 0; j < tmp; j++) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    @Test
    public void testIntToRoman() {
        assertEquals(intToRoman(3), "III");
        assertEquals(intToRoman(4), "IV");
        assertEquals(intToRoman(9), "IX");
        assertEquals(intToRoman(58), "LVIII");
        assertEquals(intToRoman(1994), "MCMXCIV");
    }
}
