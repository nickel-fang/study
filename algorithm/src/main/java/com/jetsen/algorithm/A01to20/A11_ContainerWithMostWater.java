package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A11_ContainerWithMostWater {
    /*brute force*/
    public static int maxArea(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return result;
    }

    /*two pointer approach*/
    public static int maxArea2(int[] height) {
        int start = 0, end = height.length-1;
        int result = 0;
        while (start < end) {
            result = Math.max(result, Math.min(height[start], height[end]) * (end - start));
            if (height[start] > height[end])
                end--;
            else
                start++;
        }
        return result;
    }

    @Test
    public void testMaxArea() {
        assertEquals(maxArea2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
    }
}
