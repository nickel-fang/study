package com.jetsen.algorithm.other;

public class A387_FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        int[] repeat = new int[26];
        for (char c : s.toCharArray()) repeat[c - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (repeat[s.charAt(i) - 'a'] == 1) return i;
        return -1;
    }

    public int firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.indexOf(s.charAt(i))==s.lastIndexOf(s.charAt(i))) return i;
        return -1;
    }
}
