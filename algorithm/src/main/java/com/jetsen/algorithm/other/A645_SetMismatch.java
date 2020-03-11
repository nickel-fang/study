package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class A645_SetMismatch {
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int repetition = 0, miss = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) repetition = nums[i];
            else if (nums[i] > nums[i - 1] + 1) miss = nums[i - 1] + 1;
        }
        if (nums[nums.length - 1] != nums.length) miss = nums.length;
        return new int[]{repetition, miss};
    }

    //use map
    public int[] findErrorNums2(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int dup = -1, missing = -1;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(i)) {
                if (map.get(i) == 2)
                    dup = i;
            } else
                missing = i;
            if (dup != -1 && missing != -1) break;
        }
        return new int[]{dup, missing};
    }

    //use array self
    public int[] findErrorNums3(int[] nums) {
        int dup = -1, missing = 1;
        for (int n : nums) {
            if (nums[Math.abs(n) - 1] < 0)
                dup = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }

    @Test
    public void testFindErrorNums() {
        assertArrayEquals(findErrorNums(new int[]{1, 3, 3}), new int[]{3, 2});
    }
}
