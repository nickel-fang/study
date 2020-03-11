package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class A238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProduct = new int[len], rightProduct = new int[len];

        leftProduct[0] = 1;
        for (int i = 1; i < len; i++)
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        rightProduct[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--)
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];

        for (int i = 0; i < len; i++)
            nums[i] = leftProduct[i] * rightProduct[i];
        return nums;
    }

    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] product = new int[len];

        product[0] = 1;
        for (int i = 1; i < len; i++)
            product[i] = product[i - 1] * nums[i - 1];

        int tmp = 1;
        for (int i = len - 1; i >= 0; i--) {
            product[i] *= tmp;
            tmp *= nums[i];
        }
        return product;
    }

    @Test
    public void testProductExceptSelf() {
        assertArrayEquals(productExceptSelf(new int[]{0, 0}), new int[]{0, 0});
    }
}
