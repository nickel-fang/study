package com.jetsen.algorithm.A21to40;

import com.jetsen.algorithm.ListNode;

public class A23_MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) return null;

        int power = 1;
        while (power < lists.length) {
            power = power << 1;
            for (int i = 0; i <= lists.length / power; i++) {
                if ((i * power + power / 2) < lists.length)
                    lists[i * power] = mergeTwoLists(lists[i * power], lists[i * power + power / 2]);
            }

        }
        return lists[0];
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (null == lists || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode result = lists[0];

        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
