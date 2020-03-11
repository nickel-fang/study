package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class A01_TwoSum {

    /*
     * approach 1: brute force
     * time complexity: O(n*n)
     * space complexity: O(1)
     * */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /*
     * approach 2: two-pass hash table
     * time complexity: O(n)
     * space complexity: O(n)
     * */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return null;
    }

    /*
     * approach 3:one-pass hash table
     * time complexity: O(n)
     * space complexity: O(n)
     * */
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    @Test
    public void testTwoSum() {
        assertArrayEquals(A01_TwoSum.twoSum(new int[]{2, 2, 4, 8}, 6), new int[]{0, 2});
    }

    @Test
    public void testTwoSum2() {
        assertArrayEquals(A01_TwoSum.twoSum2(new int[]{2, 2, 4, 8}, 6), new int[]{0, 2});
    }

    @Test
    public void testTwoSum3() {
        assertArrayEquals(A01_TwoSum.twoSum3(new int[]{2, 2, 4, 8}, 6), new int[]{1, 2});
    }
}
