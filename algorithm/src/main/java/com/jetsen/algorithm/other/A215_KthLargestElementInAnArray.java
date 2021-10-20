package com.jetsen.algorithm.other;

import java.util.Arrays;

public class A215_KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums, (e1,e2) -> (int)e2-(int)e1);
        return nums[nums.length - k];
    }
}
