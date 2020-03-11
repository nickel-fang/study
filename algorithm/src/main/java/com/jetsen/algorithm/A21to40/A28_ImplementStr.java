package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A28_ImplementStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        char[] chars = needle.toCharArray();
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == chars[0]) {
                boolean isStr = true;
                for (int j = 1, k = i + 1; j < needle.length(); j++, k++) {
                    if (haystack.charAt(k) != chars[j]) {
                        isStr = false;
                        break;
                    }
                }
                if (isStr) return i;
            }
        }
        return -1;
    }

    @Test
    public void testStrStr() {
        assertEquals(strStr("hello", "ll"), 2);
    }
}
