package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A10_RegularExpressionMatching {
    /*
     * recursive
     * */
    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty())
            return text.isEmpty();
        boolean firstMatch = !text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    @Test
    public void testIsMatch(){
        assertEquals(isMatch("aa","a*"),true);
        assertEquals(isMatch("mississippi","mis*is*p*."),false);
        assertEquals(isMatch("aa","a"),false);
        assertEquals(isMatch("ab",".*"),true);
        assertEquals(isMatch("aab","c*a*b"),true);
    }

}
