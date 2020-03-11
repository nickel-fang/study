package com.jetsen.algorithm.A21to40;

import java.util.HashMap;
import java.util.Map;

public class A36_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        //check each row
        for (int row = 0; row < 9; row++) {
            Map<Character, Integer> nums = new HashMap<>(9);
            for (int column = 0; column < 9; column++) {
                char one = board[row][column];
                if (one != '.') {
                    if (nums.getOrDefault(one, 0) > 0) return false;
                    else nums.put(one, 1);
                }
            }
        }

        //check each column
        for (int column = 0; column < 9; column++) {
            Map<Character, Integer> nums = new HashMap<>(9);
            for (int row = 0; row < 9; row++) {
                char one = board[row][column];
                if (one != '.') {
                    if (nums.getOrDefault(one, 0) > 0) return false;
                    else nums.put(one, 1);
                }
            }
        }

        //check each grid
        for (int grid = 0; grid < 9; grid++) {
            Map<Character, Integer> nums = new HashMap<>(9);
            int modulo = grid / 3, remainder = grid % 3;
            for (int row = modulo * 3; row < modulo * 3 + 3; row++) {
                for (int column = remainder * 3; column < remainder * 3 + 3; column++) {
                    char one = board[row][column];
                    if (one != '.') {
                        if (nums.getOrDefault(one, 0) > 0) return false;
                        else nums.put(one, 1);
                    }
                }
            }
        }

        return true;
    }

    //一次遍历
    public boolean isValidSudoku2(char[][] board) {
        HashMap<Character, Integer>[] rows = new HashMap[9];
        HashMap<Character, Integer>[] columns = new HashMap[9];
        HashMap<Character, Integer>[] grids = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            grids[i] = new HashMap<>();
        }

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char num = board[row][column];
                if (num != '.') {
                    if (rows[row].getOrDefault(num, 0) > 0) return false;
                    else rows[row].put(num, 1);
                    if (columns[column].getOrDefault(num, 0) > 0) return false;
                    else columns[column].put(num, 1);
                    int gridIndex = row / 3 * 3 + column / 3;
                    if (grids[gridIndex].getOrDefault(num, 0) > 0) return false;
                    else grids[gridIndex].put(num, 1);
                }
            }
        }
        return true;
    }
}
