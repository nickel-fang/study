package com.jetsen.algorithm.A01to20;

public class A19_RemoveNthNodeFromEndOfList {
    /*use two point
    * the first point skips n+1 elements
    * then the first and second point concurrently skip elements until the first is null
    * the second point just point the nth element which should be deleted
    * */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;

        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
