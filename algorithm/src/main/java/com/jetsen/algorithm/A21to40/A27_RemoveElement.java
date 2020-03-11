package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val)
                nums[i++] = nums[j];
        }
        return i;
    }

    public int removeElement2(int[] nums, int val) {
        int i = 0, j = nums.length;
        while (i < j) {
            if (nums[i] == val)
                nums[i] = nums[--j];
            else
                i++;
        }
        return i;
    }

    @Test
    public void testRemoveElement() {
        assertEquals(removeElement2(new int[]{2,3,3}, 3), 1);
        assertEquals(removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2), 5);
    }
}
