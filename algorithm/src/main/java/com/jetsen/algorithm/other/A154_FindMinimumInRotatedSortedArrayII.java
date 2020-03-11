package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A154_FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int middle = (left + right + 1) / 2;
        if (nums[middle] < nums[middle - 1]) return nums[middle];
        if (nums[middle] > nums[middle - 1]) {
            if (nums[middle] <= nums[right])
                return findMin(nums, left, middle - 1);
            else if (nums[middle] > nums[right])
                return findMin(nums, middle + 1, right);
        } else {
            if (nums[middle] < nums[right])
                return findMin(nums, left, middle - 1);
            else if (nums[middle] > nums[right])
                return findMin(nums, middle + 1, right);
            else
                return Math.min(findMin(nums, left, middle - 1), findMin(nums, middle, right));
        }
        return nums[left];
    }

    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[right]) left = middle + 1;
            else if (nums[middle] < nums[right]) right = middle;
            else right = right - 1;
        }
        return nums[left];
    }

    @Test
    public void testFindMin() {
        assertEquals(findMin(new int[]{1, 3, 3, 3}), 1);
        assertEquals(findMin(new int[]{1, 3, 5}), 1);
    }
}
