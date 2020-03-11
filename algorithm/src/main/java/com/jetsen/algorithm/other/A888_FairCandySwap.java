package com.jetsen.algorithm.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class A888_FairCandySwap {
    //能过将2个数组排序，再双指针查找
    public int[] fairCandySwap(int[] A, int[] B) {
        int differ = 0, sumA = 0, sumB = 0;
        for (int x : A) sumA += x;
        for (int x : B) sumB += x;
        differ = (sumA - sumB) / 2;

        Arrays.sort(A);
        Arrays.sort(B);

        int aStart = 0, bStart = 0;
        while (aStart < A.length && bStart < B.length) {
            int tmp = differ - A[aStart] + B[bStart];
            if (tmp == 0) return new int[]{A[aStart], B[bStart]};
            else if (tmp < 0) bStart++;
            else aStart++;
        }
        return null;
    }

    //无须排序，将B数纳入set中
    public int[] fairCandySwap2(int[] A, int[] B) {
        int differ = 0, sumA = 0, sumB = 0;
        for (int x : A) sumA += x;
        for (int x : B) sumB += x;
        differ = (sumA - sumB) / 2;

        Set<Integer> setB = new HashSet<>();
        for (int x : B) setB.add(x);

        for (int x : A) {
            if (setB.contains(x - differ)) return new int[]{x, x - differ};
        }
        return null;
    }
}
