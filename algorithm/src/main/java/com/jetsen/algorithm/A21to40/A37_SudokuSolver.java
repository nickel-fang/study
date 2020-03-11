package com.jetsen.algorithm.A21to40;

public class A37_SudokuSolver {
    boolean[][] rows = new boolean[9][10];
    boolean[][] columns = new boolean[9][10];
    boolean[][] grids = new boolean[9][10];
    char[][] board;
    boolean isSolved;

    public void solveSudoku(char[][] board) {
        this.board = board;
        //init rows, columns, grids
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char num = board[row][column];
                if (num != '.') {
                    placeNumber(Character.getNumericValue(num), row, column);
                }
            }
        }

        backtrack(0, 0);
    }

    private void backtrack(int row, int column) {
        if (board[row][column] == '.') {
            for (int num = 1; num < 10; num++) {
                if (couldPlace(num, row, column)) {
                    placeNumber(num, row, column);
                    placeNextNumber(row, column);
                    if (!isSolved) removeNumber(num, row, column);
                }
            }
        } else {
            placeNextNumber(row, column);
        }
    }

    private void removeNumber(int num, int row, int column) {
        int gridsIndex = row / 3 * 3 + column / 3;
        rows[row][num] = false;
        columns[column][num] = false;
        grids[gridsIndex][num] = false;
        board[row][column] = '.';
    }

    private void placeNextNumber(int row, int column) {
        if (row == 8 && column == 8) {
            isSolved = true;
        } else {
            if (column == 8) backtrack(row + 1, 0);
            else backtrack(row, column + 1);
        }
    }

    private boolean couldPlace(int num, int row, int column) {
        int gridsIndex = row / 3 * 3 + column / 3;
        return !(rows[row][num] || columns[column][num] || grids[gridsIndex][num]);
    }

    private void placeNumber(int num, int row, int column) {
        int gridsIndex = row / 3 * 3 + column / 3;
        rows[row][num] = true;
        columns[column][num] = true;
        grids[gridsIndex][num] = true;
        board[row][column] = (char) (num + '0');
    }
}
