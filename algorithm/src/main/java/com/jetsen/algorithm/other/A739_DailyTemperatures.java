package com.jetsen.algorithm.other;

import java.util.LinkedList;

/**
 * @author: Nickel Fang
 * @date: 2021/10/15 15:49
 */
public class A739_DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        LinkedList<Info> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()) {
                Info info = stack.peekLast();
                if (info.num < temperatures[i]) {
                    result[info.index] = i - info.index;
                    stack.removeLast();
                } else {
                    break;
                }
            }
            stack.add(new Info(temperatures[i], i));
        }
        return result;
    }

    public static void main(String[] args) {
        A739_DailyTemperatures test = new A739_DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = test.dailyTemperatures(temperatures);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}

class Info {
    public int num;
    public int index;

    public Info(int temperature, int i) {
        this.num = temperature;
        this.index = i;
    }
}
