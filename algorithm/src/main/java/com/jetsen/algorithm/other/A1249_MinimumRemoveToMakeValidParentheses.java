package com.jetsen.algorithm.other;

import java.util.Deque;
import java.util.LinkedList;

public class A1249_MinimumRemoveToMakeValidParentheses {
    public static String minRemoveToMakeValid(String s) {
        int difference = 0;

        Deque<Integer> deque = new LinkedList();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (difference == 0 && c == ')') {
                deque.add(i);
            } else if (difference > 0 && c == ')') {
                deque.removeLast();
                difference--;
            } else if (c == '(') {
                deque.add(i);
                difference++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int start = 0, end;
        while (deque.size() > 0) {
            end = deque.pollFirst();
            sb.append(s.substring(start, end));
            start = end + 1;
        }
        sb.append(s.substring(start));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }
}
