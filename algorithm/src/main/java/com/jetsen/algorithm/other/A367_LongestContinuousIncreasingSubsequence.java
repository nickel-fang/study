package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A367_LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int longest = 0, point = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                longest = Math.max(i - point, longest);
                point = i;
            }
        }
        longest = Math.max(nums.length-point,longest);
        return longest;
    }

    @Test
    public void testFindlengthOfLCIS() {
        assertEquals(findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}), 3);
        assertEquals(findLengthOfLCIS(new int[]{2, 2, 2, 2, 2,}), 1);
    }
}
