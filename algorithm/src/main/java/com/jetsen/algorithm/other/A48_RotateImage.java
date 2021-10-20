package com.jetsen.algorithm.other;

public class A48_RotateImage {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j < len - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }
}
