package com.jetsen.algorithm.other;


import java.util.LinkedList;
import java.util.List;

public class A78_Subsets {

    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {

        int start = 0, end = nums.length - 1;
        getSets(nums, start, end);

        return result;
    }

    private void getSets(int[] nums, int start, int end) {
        if (start > end) {
            List<Integer> single = new LinkedList<>();
            result.add(single);
        } else {
            getSets(nums, start, end - 1);
            List<List<Integer>> temps = new LinkedList<>(result);
            for (List<Integer> temp : temps) {
                List<Integer> single = new LinkedList<>(temp);
                single.add(nums[end]);
                result.add(single);

            }
        }
    }
}
