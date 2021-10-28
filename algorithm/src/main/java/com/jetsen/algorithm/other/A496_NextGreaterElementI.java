package com.jetsen.algorithm.other;

import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: Nickel Fang
 * @date: 2021/10/26 11:13
 */
public class A496_NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);

        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);

        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 2, 4};
        int[] nums2 = {6, 5, 4, 3, 2, 1, 7};
        A496_NextGreaterElementI test = new A496_NextGreaterElementI();
        nums1 = test.nextGreaterElement(nums1, nums2);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }
}
