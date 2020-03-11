package com.jetsen.algorithm.A01to20;

import java.util.Stack;

public class A20_ValidParentheses {
    /*{} () []*/
    public static boolean isValid(String s) {
        if ((s.length() & 1) == 1) return false;
        Stack<Character> stack = new Stack<Character>();
        char temp;
        for (Character ch : s.toCharArray()) {
            if (ch == '{' || ch == '(' || ch == '[') stack.push(ch);
            else {
                if (stack.isEmpty()) return false;
                temp = stack.pop();
                if (ch == '}' && temp != '{') return false;
                if (ch == ')' && temp != '(') return false;
                if (ch == ']' && temp != '[') return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
