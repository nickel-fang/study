package com.jetsen.algorithm.other;

import com.jetsen.algorithm.TreeNode;

/**
 * @author: Nickel Fang
 * @date: 2021/10/25 10:01
 */
public class A222_CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        // time complexity. O(n)
        //return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);

        // utilize the feature of complete binary tree
        if (root == null) return 0;
        // 1. find the depth of a complete binary tree
        TreeNode node = root;
        int depth = 0;
        while (node.left != null) {
            depth++;
            node = node.left;
        }

        // 2. use binary search
        int l = 1 << depth, r = (1 << (depth + 1)) - 1;
        while (l < r) {
            int mid = (r - l + 1) / 2 + l;
            if (exists(root, depth, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

    public boolean exists(TreeNode root, int depth, int mid) {
        int bits = 1 << (depth - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & mid) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        A222_CountCompleteTreeNodes test = new A222_CountCompleteTreeNodes();
        System.out.println(test.countNodes(node1));
    }

}
