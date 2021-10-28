package com.jetsen.algorithm.other;

public class A242_ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        int[] a = new int[26];

        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
            a[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (a[i] != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }
}
