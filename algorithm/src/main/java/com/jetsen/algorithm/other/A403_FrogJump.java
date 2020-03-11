package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A403_FrogJump {
    //动态规划，通过hashMap存储
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++)
            map.put(stones[i], new HashSet<>());
        map.get(0).add(0);

        for (int i = 0; i < stones.length; i++) {
            for (int lastJump : map.get(stones[i])) {
                for (int currentJump = lastJump + 1; currentJump >= lastJump - 1; currentJump--) {
                    if (currentJump > 0 && map.containsKey(currentJump + stones[i])) {
                        if (currentJump + stones[i] == stones[stones.length - 1]) return true;
                        map.get(currentJump + stones[i]).add(currentJump);
                    }
                }
            }
        }

        return map.get(stones[stones.length - 1]).size() > 0;
    }

    //动态规划，使用数组
    public boolean canCross2(int[] stones) {
        int len = stones.length;
        boolean[][] dp = new boolean[len][len + 1];
        dp[0][1] = true;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= i / 2; j--) {
                int step = stones[i] - stones[j];
                if (step > len || !dp[j][step]) continue;
                dp[i][step] = true;
                if (step + 1 <= len) dp[i][step + 1] = true;
                if (step - 1 >= 0) dp[i][step - 1] = true;
                if (i == len - 1) return true;
            }
        }
        return false;
    }

    //使用stack
    public boolean canCross3(int[] stones) {
        for (int i = 3; i < stones.length; i++) {
            if (stones[i] > 2 * stones[i - 1])
                return false;
        }

        Set<Integer> stonePosition = new HashSet<>();
        for (int stone : stones)
            stonePosition.add(stone);

        Stack<Integer> positions = new Stack<>();
        Stack<Integer> jumps = new Stack<>();
        positions.push(0);
        jumps.push(0);
        while (!positions.isEmpty()) {
            int position = positions.pop();
            int jump = jumps.pop();
            for (int i = jump - 1; i <= jump + 1; i++) {
                if (i > 0) {
                    int nextPosition = position + i;
                    if (nextPosition == stones[stones.length - 1]) return true;
                    else if (stonePosition.contains(nextPosition)) {
                        positions.add(nextPosition);
                        jumps.add(i);
                    }
                }
            }
        }
        return false;
    }

    @Test
    public void testCanCross() {
        assertEquals(canCross2(new int[]{0, 1, 2, 3, 4, 8, 9, 11}), true);
        assertEquals(canCross2(new int[]{0, 1, 3, 6, 10, 15, 16, 21}), true);
    }
}
