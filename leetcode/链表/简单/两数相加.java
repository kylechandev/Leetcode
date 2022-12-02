/*
 * @Author: kaic
 * @Date: 2022-11-13 09:33:46
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 09:59:44
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.简单;

/**
 * 2. 两数相加
 * 
 * 简单、链表
 * 
 * https://leetcode.cn/problems/add-two-numbers/
 * 
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class 两数相加 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 时间复杂度：O(max(m,n))
     * 空间复杂度：O(max(m,n))
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1、创建结果链表
        ListNode head = new ListNode(0); // 保留头节点位置，避免后续链表节点指针移动后导致头节点丢失
        ListNode current = head; // 当前节点
        // 进位标记
        boolean carry = false;

        // 2、同时遍历两个链表
        ListNode l1Num = l1;
        ListNode l2Num = l2;
        while (l1Num != null || l2Num != null) {
            int n1 = l1Num != null ? l1Num.val : 0;
            int n2 = l2Num != null ? l2Num.val : 0;

            int sum = n1 + n2;
            if (carry) {
                sum++;
            }

            ListNode node;
            if (sum >= 10) {
                // 添加个位数
                node = new ListNode(sum % 10);
                carry = true;
            } else {
                node = new ListNode(sum);
                carry = false;
            }
            current.next = node;
            current = current.next;

            if (l1Num != null) {
                l1Num = l1Num.next;
            }
            if (l2Num != null) {
                l2Num = l2Num.next;
            }
        }

        // 最后一位进位
        if (carry) {
            current.next = new ListNode(1);
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;

        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;

        // 342 + 465
        // 807

        ListNode head = addTwoNumbers(l1, l2);
        System.out.print("输出结果：");
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
    }
}
