package com.jetsen.algorithm.other;

import java.util.PriorityQueue;

/**
 * @author: Nickel Fang
 * @date: 2021/10/11 15:54
 */
public class A240_SearchA2DMatrixII {
    int[][] matrix;
    int target;

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) col--;
            else if (matrix[row][col] < target) row++;
            else return true;
        }
        return false;
    }

    //binary search
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            if (verticalFound) return true;
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (horizontalFound) return true;
        }
        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int start, boolean isVertical) {
        int end = isVertical ? matrix.length - 1 : matrix[0].length - 1;
        int fixed = start;
        while (start <= end) {
            int middle = (end + start) / 2;
            if (isVertical) {
                if (matrix[middle][fixed] == target) {
                    return true;
                } else if (matrix[middle][fixed] > target) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else {
                if (matrix[fixed][middle] == target) {
                    return true;
                } else if (matrix[fixed][middle] > target) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            }
        }
        return false;
    }

    //矩阵划分四个小矩阵，递归查找
    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        this.matrix = matrix;
        this.target = target;

        return searchRec(0, 0, matrix[0].length - 1, matrix.length - 1);
    }

    private boolean searchRec(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) return false;
        if (target < matrix[y1][x1] || target > matrix[y2][x2]) return false;
        int midX = (x1 + x2) / 2;

        int indexY = y1;
        while (indexY <= y2 && matrix[indexY][midX] <= target) {
            if (matrix[indexY][midX] == target) return true;
            indexY++;
        }
        return searchRec(x1, indexY, midX - 1, y2) || searchRec(midX + 1, y1, x2, indexY - 1);

    }

    public static void main(String[] args) {
//        int[][] nums = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] nums = {{1, 1}};
        A240_SearchA2DMatrixII a = new A240_SearchA2DMatrixII();
        System.out.println(a.searchMatrix3(nums, 1));
    }
}
