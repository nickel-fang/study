package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || nums[0] >= target) return 0;
        if (nums[nums.length - 1] < target) return nums.length;

        int start = 0, end = nums.length - 1;
        int middle = (start + end) / 2;
        while (start < end) {
            if (nums[middle] == target) return middle;
            if (nums[middle] > target) end = middle;
            else start = middle + 1;
            middle = (start + end) / 2;
        }
        return start;
    }

    @Test
    public void testsearchInsert() {
        assertEquals(searchInsert(new int[]{1, 3, 5, 6}, 2), 1);
    }
}
