package com.jetsen.algorithm.A21to40;

import com.jetsen.algorithm.ListNode;

public class A24_SwapNodesInParis {
    /*recursive*/
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode firstNode = head, secondNode = head.next;

        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }

    /*iteration*/
    public ListNode swapPair2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevNode = dummy;
        while ((head != null) && (head.next != null)) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            prevNode = firstNode;
            head = firstNode.next;
        }
        return dummy.next;
    }
}
