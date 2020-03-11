package com.jetsen.algorithm.other;

public class A326_PowerOfThree {
    //10进制转换为3进制
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    //通过数学公式
    //epsilon 没搞清楚
    public boolean isPowerOfThree2(int n) {
        int epsilon = 1;
        return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }

    //利用整数的最大值，获取3的幂数最大值。然后与n整除来判断
    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
