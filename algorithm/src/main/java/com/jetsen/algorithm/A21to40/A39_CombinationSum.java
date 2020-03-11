package com.jetsen.algorithm.A21to40;

import java.util.*;

public class A39_CombinationSum {
    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        this.candidates = candidates;
        backtrack(new ArrayDeque<>(), target, 0);
        return result;
    }

    private void backtrack(Deque<Integer> stack, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            stack.addFirst(candidates[i]);
            backtrack(stack, target - candidates[i], i);
            stack.removeFirst();
        }
    }

}
