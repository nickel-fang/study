package com.jetsen.algorithm.other;

import java.util.Arrays;
import java.util.Set;

/*
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 * */
public class A1349_MaximumStudentsTakingExam {
    public int maxStudents(char[][] seats) {
        int row = seats.length;
        int col = seats[0].length;
        int[][] dp = new int[row + 1][1 << col];

        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < (1 << col); j++) {
                boolean ok = true;
                //Check the same line if there is a situation, that two student set near by.
                for (int k = 0; k < col; k++) {
                    if (checkBit(j, k) && seats[i - 1][k] == '#' || (k < col - 1 && checkBit(j, k) && checkBit(j, k + 1))) {
                        ok = false;
                        break;
                    }
                }

                if (!ok) {
                    // Here because the j-th situation break the didn't fit the requirement, we skip this situation
                    dp[i][j] = -1;
                    continue;
                }

                for (int last = 0; last < (1 << col); last++) {
                    if (dp[i - 1][last] == -1) continue;
                    ok = true;
                    for (int t = 0; t < col; t++) {
                        if (checkBit(last, t) && ((t > 0 && checkBit(j, t - 1)) || (t < col - 1 && checkBit(j, t + 1)))) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) dp[i][j] = Math.max(dp[i][j], dp[i - 1][last] + cntBits(j));
                }
            }
        }
        int res = 0;
        for (int i = 0; i < (1 << col); i++) {
            res = Math.max(res, dp[row][i]);
        }
        return res;
    }

    private boolean checkBit(int num, int pos) {
        return (num & (1 << pos)) > 0;
    }

    private int cntBits(int num) {
        int cnt = 0;
        while (num > 0) {
            cnt++;
            num &= num - 1;
        }
        return cnt;
    }
}
