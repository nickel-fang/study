package com.jetsen.algorithm.other;

public class A84_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int maxArea = heights[0];
        for (int i = 0; i < size; i++) {
            int minHeight = heights[i];
            for (int j = i; j < size; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                if (minHeight * (size - i) < maxArea) break;
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea(new int[]{2, 4}));
    }
}
