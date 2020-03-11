package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A14_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        char common;
        int start = 0;

        while (true) {
            if (start == strs[0].length()) return sb.toString();
            common = strs[0].charAt(start);
            for (int i = 1; i < strs.length; i++) {
                if (start == strs[i].length()) return sb.toString();
                if (common != strs[i].charAt(start))
                    return sb.toString();
            }
            sb.append(common);
            start++;
        }
    }

    @Test
    public void testLongestCommonPrefix() {
        assertEquals(longestCommonPrefix(new String[]{"flower", "flow", "flight"}), "fl");
        assertEquals(longestCommonPrefix(new String[]{"dog", "racecar", "car"}), "");
    }
}
