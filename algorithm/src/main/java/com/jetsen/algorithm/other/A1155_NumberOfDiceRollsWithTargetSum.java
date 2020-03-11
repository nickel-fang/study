package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A1155_NumberOfDiceRollsWithTargetSum {
    public int numRollsToTarget(int d, int f, int target) {
        if (target > d * f || target < d) return 0;

        int values[][] = new int[d + 1][target + 1];
        for (int column = 1; column <= Math.min(f, target); column++) {
            values[1][column] = 1;
        }
        for (int row = 2; row <= d; row++) {
            for (int column = row; column <= Math.min(target, d * f); column++) {
                for (int tmp = 1; tmp <= f && column - tmp > 0; tmp++) {
                    values[row][column] = (values[row][column] + values[row - 1][column - tmp]) % 1000000007;
                }

            }
        }
        return values[d][target];
    }

    @Test
    public void testNumRollsToTarget() {
        assertEquals(numRollsToTarget(1, 6, 3), 1);
        assertEquals(numRollsToTarget(2, 6, 7), 6);
        assertEquals(numRollsToTarget(2, 5, 10), 1);
        assertEquals(numRollsToTarget(1, 2, 3), 0);
        assertEquals(numRollsToTarget(30, 30, 500), 222616187);
    }
}
