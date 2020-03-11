package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A946_ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();
        int pushPoint = 0, popPoint = 0;
        while (pushPoint < pushed.length) {
            if (pushed[pushPoint] != popped[popPoint]) {
                if (!stack.empty() && stack.peek() == popped[popPoint]) {
                    stack.pop();
                    popPoint++;
                } else {
                    stack.push(pushed[pushPoint]);
                    pushPoint++;
                }
            } else {
                pushPoint++;
                popPoint++;
            }
        }
        while (popPoint < popped.length) {
            if (stack.pop() != popped[popPoint++]) return false;
        }
        return true;
    }

    @Test
    public void testValidateStackSequences() {
        assertEquals(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}), true);
        assertEquals(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}), false);
    }
}
