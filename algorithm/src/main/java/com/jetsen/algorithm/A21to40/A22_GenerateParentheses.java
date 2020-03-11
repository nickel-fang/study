package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A22_GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public static void backtrack(List<String> list, String current, int open, int close, int max) {
        if (current.length() == max << 1) {
            list.add(current);
            return;
        }

        if (open < max)
            backtrack(list, current + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, current + ")", open, close + 1, max);
    }

    @Test
    public void testGenerateParaenthesis() {
        assertEquals(generateParenthesis(3).size(), 5);
    }
}
