/*
 * @Author: kaic
 * @Date: 2022-12-02 17:00:28
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 21:25:29
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.简单;

import leetcode.链表.ListNode;

/**
 * 面试题02.07 链表相交
 * 
 * 简单
 * 
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 */
public class 链表相交 {

    /**
     * 暴力搜索
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null) {
            while (curB != null) {
                if (curA == curB) {
                    return curA;
                }
                curB = curB.next;
            }
            curB = headB;
            curA = curA.next;
        }

        return null;
    }

    /**
     * 双指针解法
     * 
     * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/solutions/1190240/mian-shi-ti-0207-lian-biao-xiang-jiao-sh-b8hn/
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;

        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }

        return A;
    }

    /**
     * 借助HashMap
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        // 1、将headA的所有节点添加到hashmap中
        // 2、遍历headB，判断是否存在hashmap中
        // 3、存在，返回

        return null;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);

        a1.next = a2;
        a2.next = c1;
        c1.next = c2;
        c2.next = c3;
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;

        ListNode.printListNode(new 链表相交().getIntersectionNode2(a1, b1));
    }
}
