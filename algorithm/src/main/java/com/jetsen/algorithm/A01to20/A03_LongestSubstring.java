package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A03_LongestSubstring {
    /*
     * approach: brute force
     * time complexity: O(n*n*n)
     * space complexity: O(min(m,n)) stored in the set
     * */
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (allUnique(s, i, j)) result = Math.max(result, j - i);
            }
        }
        return result;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<Character>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /*
     *approach: sliding window
     * time complexity: O(2n)
     * space complexity: O(min(m,n))
     *  */
    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        Set<Character> set = new HashSet<Character>();
        int result = 0, i = 0, j = 0;
        while (i < len && j < len) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                result = Math.max(result, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return result;
    }

    /*
     * approach: sliding window optimized
     * time complexity: O(n)
     * space complexity: O(min(m,n))
     * */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /*
     * approach: sliding window optimized. because of the size of ASCII characters, we can use a fixed array
     * time complexity: O(n)
     * space complexity: O(m)*/
    public static int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)],i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j+1;
        }
        return ans;
    }

    @Test
    public void testLengthOfLongestSubstring() {
        assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
    }

    @Test
    public void testLengthOfLongestSubstring2() {
        assertEquals(lengthOfLongestSubstring2("abcabcbb"), 3,"this is a message");
        assertEquals(lengthOfLongestSubstring2("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring2("pwwkew"), 3);
    }

    @Test
    public void testLengthOfLongestSubstring3() {
        assertEquals(lengthOfLongestSubstring3("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring3("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring3("pwwkew"), 3);
    }

    @Test
    public void testLengthOfLongestSubstring4() {
        assertEquals(lengthOfLongestSubstring4("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring4("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring4("pwwkew"), 3);
    }
}
