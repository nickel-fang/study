package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

public class A31_NextPermutation {
    public void nextPermutation(int[] nums) {
        boolean hasNextPermutation = false;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = nums.length - 1;
                while (j >= i && nums[j] <= nums[i - 1]) j--;
                //交换两值
                swap(nums, j, i - 1);

                //后面的进行反转
                reverse(nums, i);
                hasNextPermutation = true;
                break;
            }
        }
        if (!hasNextPermutation) reverse(nums, 0);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) swap(nums, start++, end--);
    }

    @Test
    public void testNextPermutation() {
        nextPermutation(new int[]{3, 8, 6, 6, 9, 7, 5});
    }
}
