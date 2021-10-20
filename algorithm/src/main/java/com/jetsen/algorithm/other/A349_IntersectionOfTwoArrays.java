package com.jetsen.algorithm.other;

import lombok.val;

public class A349_IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        int[] bits = new int[33];
        int[] bits2 = new int[33];

        for (int i = 0; i < nums1.length; i++) {
            bits[nums1[i] / 31] |= 1 << nums1[i] % 31;
        }

        for (int i = 0; i < nums2.length; i++) {
            bits2[nums2[i] / 31] |= 1 << nums2[i] % 31;
        }

        //判断返回数组的大小
        int num = 0;
        for (int i = 0; i < 33; i++) {
            bits[i] &= bits2[i];
            int temp = bits[i];
            for (int j = 0; j < 31; j++) {
                num += temp % 2;
                temp >>= 1;
            }
        }

        int[] result = new int[num];
        int k = 0;
        for (int i = 0; i < 33; i++) {
            int temp = bits[i];
            for (int j = 0; j < 31; j++) {
                if (temp % 2 == 1) {
                    result[k++] = 31 * i + j;
                }
                temp >>= 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
