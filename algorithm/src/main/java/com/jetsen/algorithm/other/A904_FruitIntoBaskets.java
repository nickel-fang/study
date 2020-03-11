package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A904_FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        int num1 = tree[0], num1Count = 0, num2 = -1, num2Count = 0;
        int start = 0, point = 0;
        int max = 0;

        for (; point < tree.length; point++) {
            if (tree[point] == num1) num1Count++;
            if (tree[point] != num1) {
                num2 = tree[point++];
                num2Count++;
                break;
            }
        }

        if (num2 != -1) {
            for (; point < tree.length; point++) {
                if (tree[point] == num1) num1Count++;
                else if (tree[point] == num2) num2Count++;
                else {
                    max = Math.max(max, point - start);
                    for (int i = start; i < point; i++) {
                        if (tree[i] == num1) num1Count--;
                        else num2Count--;
                        if (num1Count == 0 || num2Count == 0) {
                            start = ++i;
                            break; //只要有一个数为0，退出循环
                        }
                    }
                    if (num1Count == 0) {
                        num1 = tree[point];
                        num1Count++;
                    } else {
                        num2 = tree[point];
                        num2Count++;
                    }
                }
            }
        }
        return Math.max(point - start, max);
    }

    @Test
    public void testTotalFruit() {
        assertEquals(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}), 5);
    }

}
