package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A572_SubtreeOfAnotherTree {
    //use preorder traversal
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }

    private String preorder(TreeNode s, boolean left) {
        if (s == null) {
            if (left) return "ln";
            else return "rn";
        }
        return "#" + s.val + preorder(s.left, true) + preorder(s.right, false);
    }


    //by comparison of nodes
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    private boolean equals(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void testIsSubtree() {
        TreeNode s = new TreeNode(1);
        s.left = new TreeNode(2);
        s.right = new TreeNode(3);

        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        assertEquals(isSubtree(s, t), true);
    }
}
