package com.jetsen.algorithm.other;

public class A101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null ^ tree2 == null) return false;
        if (tree1 == null && tree2 == null) return true;
        return tree1.val == tree2.val && isMirror(tree1.left, tree2.right) && isMirror(tree1.right, tree2.left);
    }
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
