package com.jetsen.algorithm.other;

public class A8_StringToInt {
    public static int myAtoi(String s) {

        boolean isNegative = false;

        boolean isHead = true;

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isHead) { //允许出现符号或空格
                if (c == '-') {
                    isNegative = true;
                    isHead = false;
                } else if (c == '+') {
                    isHead = false;
                } else if (c >= '0' && c <= '9') {
                    result = result * 10 + (c - '0');
                    isHead = false;
                } else if (c == ' ') {
                    //啥都不用处理，直接跳过
                } else {
                    break; //退出循环
                }
            } else {
                if (c >= '0' && c <= '9') {
                    //此处需要判断整数边界
                    if (isNegative) {
                        if (-result < Integer.MIN_VALUE / 10 || (-result == Integer.MIN_VALUE / 10 && ('0' - c) <= Integer.MIN_VALUE % 10))
                            return Integer.MIN_VALUE;
                    } else {
                        if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (c - '0') >= Integer.MAX_VALUE % 10))
                            return Integer.MAX_VALUE;
                    }
                    result = result * 10 + (c - '0');
                } else {
                    break;
                }
            }
        }
        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-2147483649"));
    }
}
