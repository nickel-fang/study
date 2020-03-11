package com.jetsen.algorithm.A21to40;

import java.util.*;

public class A40_CombinationSum2 {
    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            stack.addFirst(candidates[i]);
            backtrack(stack, target - candidates[i], i + 1);
            stack.removeFirst();
        }
    }

}
