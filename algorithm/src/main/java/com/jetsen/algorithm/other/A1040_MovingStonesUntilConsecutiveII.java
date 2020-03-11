package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class A1040_MovingStonesUntilConsecutiveII {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int N = stones.length;
        if (stones[N - 1] - stones[0] + 1 == N) return new int[]{0, 0};

        int max = (stones[N - 1] - stones[0] + 1 - N) - Math.min(stones[1] - stones[0] - 1, stones[N - 1] - stones[N - 2] - 1);

        int left = 0, right = 0;
        int maxCapacity = 0, capacity = 0;

        int rightMaxValue = stones[left] + N - 1;
        while (right < N) {
            if (stones[right] <= rightMaxValue) {
                capacity++;
                right++;
            } else {
                maxCapacity = Math.max(maxCapacity, capacity);
                left++;
                rightMaxValue = stones[left] + N - 1;
                capacity--;
            }
        }
        maxCapacity = Math.max(maxCapacity, capacity);
        int min = N - maxCapacity;

        // solve the special cases : 1,2,3,4,7  1,4,5,6,7
        if (stones[N - 2] - stones[0] == N - 2) {
            if (stones[N - 1] - stones[N - 2] == 2) min = 1;
            else min = 2;
        } else if (stones[N - 1] - stones[1] == N - 2) {
            if (stones[1] - stones[0] == 2) min = 1;
            else min = 2;
        }
        return new int[]{min, max};
    }

    @Test
    public void testNumMovesStonesII() {
        assertArrayEquals(numMovesStonesII(new int[]{7, 4, 9}), new int[]{1, 2});
    }
}
