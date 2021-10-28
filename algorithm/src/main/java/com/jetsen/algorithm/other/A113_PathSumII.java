package com.jetsen.algorithm.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class A113_PathSumII {
    LinkedList<Integer> list = new LinkedList<>();

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {


        path(root, targetSum);
        return result;
    }

    private void path(TreeNode root, int targetSum) {
        if (root == null) return;
        list.offerLast(root.val);
        if (root.val == targetSum && root.left == null && root.right == null) {
            result.add((List<Integer>) list.clone());
        }
        path(root.left, targetSum - root.val);
        path(root.right, targetSum - root.val);

        list.pollLast();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
