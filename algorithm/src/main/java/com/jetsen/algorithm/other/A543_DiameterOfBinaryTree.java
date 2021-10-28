package com.jetsen.algorithm.other;

public class A543_DiameterOfBinaryTree {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDept = depth(root.right);
        diameter = Math.max(diameter, leftDepth + rightDept);
        return Math.max(leftDepth, rightDept) + 1;
    }

}
