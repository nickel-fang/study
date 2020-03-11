package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class A18_4Sum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            if (nums[i] + 3 * nums[n - 1] < target) continue;
            if (nums[i] * 4 > target) return result;

            for (int j = i + 1; j < n - 2; j++) {
                int firstTwoSum = nums[i] + nums[j];
                int start = j + 1, end = n - 1;

                if (firstTwoSum + 2 * nums[end] < target) continue;
                if (firstTwoSum + nums[start] * 2 > target) return result;

                while (start < end) {
                    int sum = firstTwoSum + nums[start] + nums[end];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));

                        while (start + 1 <= end && nums[start] == nums[start + 1]) start++;
                        start++;
                        while (start <= end - 1 && nums[end - 1] == nums[end]) end--;
                        end--;
                    } else if (sum > target) {
                        while (start <= end - 1 && nums[end - 1] == nums[end]) end--;
                        end--;
                    } else {
                        while (start + 1 <= end && nums[start] == nums[start + 1]) start++;
                        start++;
                    }
                }
                while (j + 1 < n && nums[j] == nums[j + 1]) j++;
            }
            while (i + 1 < n && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    @Test
    public void testFourSum() {

        List<List<Integer>> res = fourSum(new int[]{-1, 2, 2, -5, 0, -1, 4}, 3);
        assertEquals(res.size(), 2);

        res = fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11);
        assertEquals(res.size(), 1);
        assertIterableEquals(res.get(0), Arrays.asList(-5, -4, -3, 1));

        res = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        assertEquals(res.size(), 3);
        assertIterableEquals(res.get(0), Arrays.asList(-2, -1, 1, 2));
        assertIterableEquals(res.get(1), Arrays.asList(-2, 0, 0, 2));
        assertIterableEquals(res.get(2), Arrays.asList(-1, 0, 0, 1));
    }
}
