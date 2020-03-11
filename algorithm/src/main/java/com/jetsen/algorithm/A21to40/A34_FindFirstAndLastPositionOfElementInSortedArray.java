package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class A34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int left = -1, right = -1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] == target) {
                left = findLeft(nums, 0, middle, target - 0.1);
                right = findRight(nums, middle, end, target + 0.1);
                break;
            } else if (nums[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return new int[]{left, right};
    }

    private int findRight(int[] nums, int start, int end, double v) {
        if (nums[end] < v) return end;
        if (start == end) return nums[start] > v ? start - 1 : start;
        int middle = (start + end) / 2;
        if (nums[middle] > v) return findRight(nums, start, middle - 1, v);
        else return findRight(nums, middle + 1, end, v);
    }

    private int findLeft(int[] nums, int start, int end, double v) {
        if (nums[start] > v) return start;
        if (start == end) return nums[start] < v ? start + 1 : start;
        int middle = (start + end) / 2;
        if (nums[middle] > v) return findLeft(nums, start, middle - 1, v);
        else return findLeft(nums, middle + 1, end, v);
    }

    @Test
    public void testSearchRange() {
        assertArrayEquals(searchRange(new int[]{1}, 1), new int[]{0, 0});
    }
}
