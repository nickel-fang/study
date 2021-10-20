package com.jetsen.algorithm.other;

public class A450_DeleteNodeInBST {

    //取巧的方法：交换要删除节点与最小节点的value
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left != null && root.right != null) { //找到右侧最小的值
                TreeNode minNode = root.right;
                while (minNode.left != null)
                    minNode = minNode.left;
                root.val = minNode.val; //交换要删除节点与最小节点的value
                root.right = deleteNode(root.right, minNode.val);
            } else {
                TreeNode newRoot = root.left == null ? root.right : root.left;
                return newRoot;
            }
        }
        return root;
    }

    //
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left != null && root.right != null) { //找到右侧最小的值
                TreeNode parentNode = root;
                TreeNode minNode = root.right;
                while (minNode.left != null) {
                    parentNode = minNode;
                    minNode = minNode.left;
                }

                if(parentNode!=root){
                    parentNode.left = minNode.right;
                    minNode.left = root.left;
                    minNode.right = root.right;
                    root = minNode;

                }else{
                    minNode.left = root.left;
                    root = minNode;
                }

            } else {
                TreeNode newRoot = root.left == null ? root.right : root.left;
                return newRoot;
            }
        }
        return root;
    }

    public class TreeNode {
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

