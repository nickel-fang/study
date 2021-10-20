package com.jetsen.algorithm.other;

public class A21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cur.next = p1;
                p1 = p1.next;
                cur = cur.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
                cur = cur.next;
            }
        }
        cur.next = p1 == null ? p2 : p1;
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
