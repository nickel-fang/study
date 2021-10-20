package com.jetsen.algorithm.other;

public class A1000_MinimumCostToMergeStones {
    public int mergeStones(int[] stones, int k) {

        //先判断是否可以移动完
        int size = stones.length;
        int divisor = size / k, remainder = size % k;

        while (divisor > 0) {
            divisor = (divisor + remainder) / k;
            remainder = (divisor + remainder) % k;
        }
        if (remainder != 0)
            return -1;

        return 0; //TODO

    }
}
