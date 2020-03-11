package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j])
                nums[++i] = nums[j];
        }
        return i + 1;
    }

    @Test
    public void testRemoveDuplicates() {
//        assertEquals(removeDuplicates(new int[]{1, 1, 2}), 2);
        assertEquals(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}), 5);
    }
}
