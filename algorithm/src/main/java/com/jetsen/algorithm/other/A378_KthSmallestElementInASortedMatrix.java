package com.jetsen.algorithm.other;

import java.util.PriorityQueue;
import java.util.logging.Level;

public class A378_KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length, step = 0;
        int sum = 0, start = 0;

        while (true) {
            if (k <= sum) break;
            else {
                start++;
                if (start <= len) {
                    sum += ++step;
                } else {
                    sum += --step;
                }
            }
        }

        PriorityQueue<Integer> temp = new PriorityQueue<>(step);
        if (start <= len) {
            for (int i = 0; i < start; i++) {
                temp.add(matrix[i][start - 1 - i]);
            }
        } else {
            for (int i = 0; i < 2 * len - start; i++) {
                temp.add(matrix[start - len + i][len - i - 1]);
            }
        }
        for (int i = 0; i < step - (sum - k) - 1; i++) {
            temp.poll();
        }
        return temp.poll();
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2}, {1, 3}};
        int[][] matrix = {{1,3,5},{6,7,12},{11,14,14}};
        A378_KthSmallestElementInASortedMatrix test = new A378_KthSmallestElementInASortedMatrix();
        System.out.println(test.kthSmallest(matrix, 3));

    }
}
