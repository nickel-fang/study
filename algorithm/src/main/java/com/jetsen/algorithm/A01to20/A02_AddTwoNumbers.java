package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A02_AddTwoNumbers {
    /*
    * The digits are stored in reverse order
    * input: (2 -> 4 -> 3) + (5 ->6 -> 4)
    * output: 7 -> 0 -> 8
    * time complexity: O(max(m,n))
    * space complexity: O(max(m,n))
    * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.value : 0;
            int y = (q != null) ? q.value : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /*
    * The digits are stored in non-reversed order
    * input: (3 -> 4 -> 2 ) + ( 4 -> 6 ->5 )
    * output: 8 -> 0 -> 7
    * */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p=l1, q=l2, curr = dummyHead;
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        int carry = 0;

        while(p!=null || q!=null){
            if(p!=null){
                s1.push(p.value);
                p = p.next;
            }
            if(q!=null){
                s2.push(q.value);
                q = q.next;
            }
        }

        while (!s1.isEmpty() || !s2.isEmpty()){
            int x = (!s1.isEmpty())?s1.pop():0;
            int y = (!s2.isEmpty())?s2.pop():0;
            int sum = carry + x + y;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            node.next = curr.next;
            curr.next = node;
        }

        if(carry>0){
            ListNode node = new ListNode(carry);
            node.next = curr.next;
            curr.next = node;
        }
        return dummyHead.next;
    }

    @Test
    public void testAddTwoNumbers(){
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);

        ListNode l3 = addTwoNumbers(l1,l2);
        assertEquals(l3.value,7);
        assertEquals(l3.next.value,0);
        assertEquals(l3.next.next.value,8);
    }

    @Test
    public void testAddTwoNumbers2(){
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);

        ListNode l3 = addTwoNumbers2(l1,l2);
        assertEquals(l3.value,8);
        assertEquals(l3.next.value,0);
        assertEquals(l3.next.next.value,7);
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int x) {
            value = x;
        }
    }
}
