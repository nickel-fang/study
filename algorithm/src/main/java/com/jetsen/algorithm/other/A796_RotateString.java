package com.jetsen.algorithm.other;

public class A796_RotateString {
    public static boolean rotateString(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length())
            return false;

        if (s.equals(goal)) return true;
        for (int i = 1; i < s.length(); i++) {
            if (s.substring(0, i).equals(goal.substring(s.length() - i, s.length())) && s.substring(i, s.length()).equals(goal.substring(0, s.length() - i)))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));
    }
}
