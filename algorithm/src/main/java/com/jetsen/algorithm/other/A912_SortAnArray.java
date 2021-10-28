package com.jetsen.algorithm.other;

public class A912_SortAnArray {
    public int[] sortArray(int[] nums) {

        quickSort(nums, -0, nums.length - 1);
        return nums;

    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {

//            int i = new Random().nextInt(right - left + 1) + left;

//            quickSort(nums, left, lessPoint + 1);
//            quickSort(nums, morePoint - 1, right);
        }

    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        A912_SortAnArray test = new A912_SortAnArray();
        int[] nums = new int[]{-1, 2, -8, -10};
        test.sortArray(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
