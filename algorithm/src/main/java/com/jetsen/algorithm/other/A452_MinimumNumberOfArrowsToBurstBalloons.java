package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A452_MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        //bubble sort
        /*for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < points.length - i; j++) {
                if (points[j][0] > points[j + 1][0]) {
                    int[] tmp = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = tmp;
                }
            }
        }*/

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        if (points.length == 0) return 0;
        int arrows = 1, end = points[0][1], index = 0;
        while (index < points.length - 1) {
            if (points[++index][0] > end) {
                arrows++; //need another arrow
                end = points[index][1];
            }
        }
        return arrows;
    }

    @Test
    public void testFindMinArrowShots() {
        assertEquals(findMinArrowShots(new int[][]{{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}}), 2);
        assertEquals(findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}), 2);
    }
}
