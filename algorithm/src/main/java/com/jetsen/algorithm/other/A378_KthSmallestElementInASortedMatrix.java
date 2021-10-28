package com.jetsen.algorithm.other;

import java.util.PriorityQueue;

/**
 * @author: Nickel Fang
 * @date: 2021/10/11 10:41
 */
public class A378_KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> {
            return e1[0] - e2[0];
        });

        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }

        for (int i = 0; i < k - 1; i++) {
            int[] temp = queue.poll();
            if (temp[2] != len - 1) {
                queue.offer(new int[]{matrix[temp[1]][temp[2] + 1], temp[1], temp[2] + 1});
            }
        }
        return queue.poll()[0];
    }
}
