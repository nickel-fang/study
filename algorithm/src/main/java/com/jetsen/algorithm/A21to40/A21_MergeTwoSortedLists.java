package com.jetsen.algorithm.A21to40;

import com.jetsen.algorithm.ListNode;

public class A21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode point = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                point.next = l1;
                l1 = l1.next;
                point = point.next;
            } else {
                point.next = l2;
                l2 = l2.next;
                point = point.next;
            }
        }
        point.next = l1 == null ? l2 : l1;

        return dummy.next;
    }
}
