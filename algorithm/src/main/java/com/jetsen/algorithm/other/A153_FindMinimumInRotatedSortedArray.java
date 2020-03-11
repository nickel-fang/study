package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A153_FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2 + 1;
            if (nums[middle] < nums[middle - 1]) return nums[middle];
            if (nums[middle] <= nums[right]) right = middle - 1;
            else left = middle + 1;
        }
        return nums[left];
    }

    @Test
    public void testFindMin() {
        assertEquals(findMin(new int[]{3, 4, 5, 1, 2}), 1);
        assertEquals(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}), 0);
    }
}
