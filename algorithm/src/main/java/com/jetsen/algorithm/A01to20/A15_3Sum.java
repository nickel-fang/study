package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class A15_3Sum {
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int start, end, sum;

        for (int i = 0; i < nums.length - 1 && nums[i] <= 0; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;

            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    while (nums[start] == nums[++start] && start < end) ;
                    while (nums[end] == nums[--end] && start < end) ;
                } else if (sum < 0)
                    while (nums[start] == nums[++start] && start < end) ;
                else
                    while (nums[end] == nums[--end] && start < end) ;
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] + 2 * nums[n - 1] < 0) continue;
            if (nums[i] * 3 > 0) break;

            int start = i + 1, end = n - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    while (nums[start] == nums[++start] && start < end) ;
                    while (nums[end] == nums[--end] && start < end) ;
                } else if (sum > 0) {
                    while (nums[end] == nums[--end] && start < end) ;
                } else {
                    while (nums[start] == nums[++start] && start < end) ;
                }
            }

            while (i + 1 < n && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    @Test
    public void testThreeSums() {
        List<List<Integer>> res = threeSum(new int[]{0, 0, 0, 0});
        assertEquals(res.size(), 1);
        assertIterableEquals(res.get(0), Arrays.asList(0, 0, 0));

        res = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(res.size(), 2);
        assertIterableEquals(res.get(0), Arrays.asList(-1, -1, 2));
        assertIterableEquals(res.get(1), Arrays.asList(-1, 0, 1));
    }
}
