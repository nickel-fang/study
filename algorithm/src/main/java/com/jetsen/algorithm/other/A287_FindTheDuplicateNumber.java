package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A287_FindTheDuplicateNumber {
    //二分法查找
    public int findDuplicate(int[] nums) {
        int minNum = 1, maxNum = nums.length - 1;
        while (minNum < maxNum) {
            int middle = minNum + (maxNum - minNum) / 2;
            if (exist(nums, middle)) maxNum = middle;
            else minNum = middle + 1;
        }
        return minNum;
    }

    private boolean exist(int[] nums, int middle) {
        int total = 0;
        for (int num : nums) {
            if (num <= middle) total++;
            if (total > middle) return true;
        }
        return false;
    }

    //链表找环
    public int findDuplicat1(int[] nums) {
        int tortoise = 0, hare = 0;
        do {
            hare = nums[nums[hare]];  //兔子每次走2步
            tortoise = nums[tortoise];  //龟每次走1步
        } while (hare != tortoise);
        tortoise = 0;
        do {
            hare = nums[hare];
            tortoise = nums[tortoise];
        } while (hare != tortoise);
        return hare;
    }

    @Test
    public void testFindDuplicate() {
        assertEquals(findDuplicate(new int[]{1, 3, 4, 2, 2}), 2);
        assertEquals(findDuplicate(new int[]{3, 1, 3, 4, 2}), 3);
    }
}
