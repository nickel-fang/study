package com.jetsen.algorithm.A41to60;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A42_TrappingRainWater {
    public int trap(int[] height) {
        if (height.length <= 2) return 0;

        int left = 0, right = height.length - 1;
        while (left + 1 < right && height[left] <= height[left + 1]) left++;
        while (right - 1 > left && height[right] <= height[right - 1]) right--;
        int leftHeight = height[left], rightHeight = height[right];

        int capacity = 0;
        while (left < right) {
            int minHeightOfLeftAndRight = Math.min(leftHeight, rightHeight);
            if(leftHeight<rightHeight) {
                while (left + 1 < right && height[left + 1] <= minHeightOfLeftAndRight) {
                    capacity += (minHeightOfLeftAndRight - height[++left]);
                }
                leftHeight = height[++left];
            }else if(leftHeight>rightHeight){
                while(right-1>left && height[right-1]<=minHeightOfLeftAndRight){
                    capacity +=(minHeightOfLeftAndRight-height[--right]);
                }
                rightHeight = height[--right];
            }else{
                while (left + 1 < right && height[left + 1] <= minHeightOfLeftAndRight) {
                    capacity += (minHeightOfLeftAndRight - height[++left]);
                }
                leftHeight = height[++left];
                while(right-1>left && height[right-1]<=minHeightOfLeftAndRight){
                    capacity +=(minHeightOfLeftAndRight-height[--right]);
                }
                rightHeight = height[--right];
            }
        }
        return capacity;
    }

    public int trap1(int[] height) {
        if (height.length <= 2) return 0;

        int left = 0, right = height.length - 1;
        while (left + 1 < right && height[left] <= height[left + 1]) left++;
        while (right - 1 > left && height[right] <= height[right - 1]) right--;
        int leftHeight = height[left], rightHeight = height[right];

        int capacity = 0, waterline = 0;
        while (left < right) {
            int minHeightOfLeftAndRight = Math.min(leftHeight, rightHeight);
            for (int i = left + 1; i < right; i++) {
                if (height[i] < minHeightOfLeftAndRight)
                    capacity += (height[i] > waterline) ? (minHeightOfLeftAndRight - height[i]) : (minHeightOfLeftAndRight - waterline);
            }
            waterline = minHeightOfLeftAndRight;
            if (leftHeight > rightHeight) { //right左移,找到第一个比当前值大的
                while (right > left && height[--right] <= rightHeight) ;
                rightHeight = height[right];
            } else if (leftHeight < rightHeight) {//left右移，找到第一个比当前值大的
                while (left < right && height[++left] <= leftHeight) ;
                leftHeight = height[left];
            } else { //相等时，left和right同时向中间移
                while (right > left && height[--right] <= rightHeight) ;
                rightHeight = height[right];
                while (left < right && height[++left] <= leftHeight) ;
                leftHeight = height[left];
            }
        }
        return capacity;
    }

    @Test
    public void testTrap() {
        assertEquals(trap(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6}), 23);
        assertEquals(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6);
    }
}
