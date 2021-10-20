package com.jetsen.algorithm.other;

public class Random {
    public static void main(String[] args) {
//        int length = 2;
//        int roundNumber = 1000_0000;
//
//        int count[] = new int[length];
//
//        for (int i = 0; i < roundNumber; i++) {
//            count[r0to1()]++;
//        }
//
//        for (int i = 0; i < length; i++) {
//            System.out.print(count[i] + "   ");
//            System.out.println((i) + " :" + (double) count[i] / roundNumber);
//        }

        int num = 32;
        int num2 = num >>1;
        System.out.println(num);

    }

    public static int r1to5() {
        return (int) (Math.random() * 5 + 1);
    }

    public static int r0to1() {
        int num = r1to5();
        while (num == 3) {
            num = r1to5();
        }
        return num <= 2 ? 0 : 1;
    }
}
