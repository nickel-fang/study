package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A16_3SumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int start, end, diff;
        if (null == nums || nums.length < 3) return 0;
        int closest = Integer.MAX_VALUE;  //initialize the value

        for (int i = 0; i < nums.length - 2; i++) {
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                diff = nums[i] + nums[start] + nums[end] - target;
                if (Math.abs(diff) < Math.abs(closest)) closest = diff;
                if (diff < 0) start++;
                else end--;
            }
        }
        return closest + target;
    }

    @Test
    public void testThreeSumcloset() {
        assertEquals(threeSumClosest(new int[]{6, -18, -20, -7, -15, 9, 18, 10, 1, -20, -17, -19, -3, -5, -19, 10, 6, -11, 1, -17, -15, 6, 17, -18, -3, 16, 19, -20, -3, -17, -15, -3, 12, 1, -9, 4, 1, 12, -2, 14, 4, -4, 19, -20, 6, 0, -19, 18, 14, 1, -15, -5, 14, 12, -4, 0, -10, 6, 6, -6, 20, -8, -6, 5, 0, 3, 10, 7, -2, 17, 20, 12, 19, -13, -1, 10, -1, 14, 0, 7, -3, 10, 14, 14, 11, 0, -4, -15, -8, 3, 2, -5, 9, 10, 16, -4, -3, -9, -8, -14, 10, 6, 2, -12, -7, -16, -6, 10}, -52), -52);
        assertEquals(threeSumClosest(new int[]{1, 2, 4, 8, 16, 32, 64, 128}, 82), 82);
        assertEquals(threeSumClosest(new int[]{-1, 2, 1, -4}, 1), 2);
    }
}
