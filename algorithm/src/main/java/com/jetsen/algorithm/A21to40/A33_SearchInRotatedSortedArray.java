package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        int rotateIndex = findRotateIndex(nums, 0, nums.length - 1);
        if (nums[0] == target) return 0;
        int location = -1;
        if (rotateIndex == 0)
            location = search(nums, 0, nums.length - 1, target);
        else
            location = nums[0] > target ? search(nums, rotateIndex, nums.length - 1, target) : search(nums, 0, rotateIndex - 1, target);
        return location;
    }

    private int findRotateIndex(int[] nums, int start, int end) {
        if (nums[start] <= nums[end]) return start;
        int middle = (start + end) / 2;
        if (nums[middle] > nums[middle + 1]) return middle + 1;
        else if (nums[0] < nums[middle]) return findRotateIndex(nums, middle + 1, end);
        else return findRotateIndex(nums, 0, middle);
    }

    private int search(int[] nums, int start, int end, int target) {
        if (start == end) return nums[start] == target ? start : -1;
        int middle = (start + end) / 2;
        if (nums[middle] == target) return middle;
        else if (nums[middle] < target) return search(nums, middle + 1, end, target);
        else return search(nums, start, end - 1, target);
    }

    @Test
    public void testSearch() {
        assertEquals(search(new int[]{3, 4, 5, 6, 1, 2}, 2), 5);
    }
}
