package com.jetsen.algorithm.A41to60;

public class A41_FindMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int contain = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                contain++;
                break;
            }
        }
        if (contain == 0) return 1;

        if (len == 1) return 2;

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len) nums[i] = 1;
        }

        for (int i = 0; i < len; i++) {
            int a = Math.abs(nums[i]);
            if (a == len) nums[0] = -Math.abs(nums[0]);
            else nums[a] = -Math.abs(nums[a]);
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) return i;
        }
        if (nums[0] > 0) return len;
        return len + 1;
    }
}
