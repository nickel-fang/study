package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A05_LongestPalindromSubstring {

    @Test
    public void testLongestPalindrome() {
        assertEquals(longestPalindrome("babad"), "bab");
        assertEquals(longestPalindrome("cbbd"), "bb");
        assertEquals(longestPalindrome("reqadfdeqaqe"), "eqaqe");
    }

    public static String longestPalindrome(String s) {
        int longest = 0;
        int size = s.length();
        int type = 0;
        int index = 0;
        for (int i = 0; i < size; i++) {
            int len1 = getLongestString(i, s);
            int len2 = getLongestString2(i, s);
            if (len1 > longest) {
                longest = len1;
                type = 1;
                index = i;
            } else if (len2 > longest) {
                longest = len2;
                type = 2;
                index = i;
            }
        }
        if (type == 1) {
            int start = index - longest / 2;
            int end = index + longest / 2;
            return s.substring(index - longest / 2, index + longest / 2 + 1);
        } else if (type == 2) {
            return s.substring(index - longest / 2 + 1, index + longest / 2 + 1);
        }
        return null;
    }

    public static int getLongestString(int index, String s) {
        int halfSize = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((index - i) < 0 || (index + i) == s.length()) {
                break;
            }
            if (s.charAt(index - i) == s.charAt(index + i)) {
                halfSize = i;
            } else {
                break;
            }
        }
        return 1 + 2 * halfSize;
    }

    public static int getLongestString2(int index, String s) {
        int halfSize = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((index - i) < 0 || (index + i + 1) == s.length()) {
                break;
            }
            if (s.charAt(index - i) == s.charAt(index + i + 1)) {
                halfSize = i;
            } else {
                break;
            }
        }
        return 2 * (halfSize + 1);
    }
}
