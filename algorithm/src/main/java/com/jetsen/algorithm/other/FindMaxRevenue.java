package com.jetsen.algorithm.other;

public class FindMaxRevenue {
    public static int getMaxRevenue(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[j] - nums[i]);
            }
        }
        return max;
    }

    public static int getMaxRevenue2(int[] nums) {
        int leftValue = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - leftValue > max) {
                max = nums[i] - leftValue;
            } else if (nums[i] < leftValue) {
                leftValue = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int maxRevenue = getMaxRevenue(new int[]{662, 660, 665, 640, 645, 668, 633, 635, 650, 664, 630, 620, 649, 630, 800});
        int maxRevenue2 = getMaxRevenue2(new int[]{662, 660, 665, 640, 645, 668, 633, 635, 650, 664, 630, 620, 649, 630, 800});
        System.out.println(maxRevenue);
        System.out.println(maxRevenue2);
    }
}
