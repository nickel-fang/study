package com.jetsen.algorithm.other;

/*use segment tree which stores in array*/
public class A307_RangeSumQuery_Mutable {
    int[] tree;
    int n;

    public A307_RangeSumQuery_Mutable(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[2 * n];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; i--)
            tree[i] = tree[2 * i] + tree[2 * i + 1];
    }

    public void update(int i, int val) {
        i += n;
        tree[i] = val; // update the leaf value
        int left = i, right = i;
        while (i > 0) {
            left = i;
            right = i;
            if (i % 2 == 0) right++;
            else left--;
            tree[i / 2] = tree[left] + tree[right];
            i = i / 2;
        }
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        i = i + n;
        j = j + n;
        while (i <= j) {
            if (i % 2 == 1) sum += tree[i++];
            if (j % 2 == 0) sum += tree[j--];
            i = i / 2;
            j = j / 2;
        }
        return sum;
    }
}
