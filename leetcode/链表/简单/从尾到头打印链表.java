/*
 * @Author: kaic
 * @Date: 2023-05-06 17:12:27
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-06 17:24:15
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.简单;

import java.util.Arrays;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 
 * 简单
 * 
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 */
public class 从尾到头打印链表 {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[] {};
        }

        // 先计算链表节点个数
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }

        int[] ans = new int[count];

        look(head, ans);

        return ans;
    }

    private int index = 0;

    private void look(ListNode head, int[] ans) {
        if (head == null) {
            return;
        }

        look(head.next, ans);
        ans[index++] = head.val;
    }

    public static void main(String[] args) {
        从尾到头打印链表 demo = new 从尾到头打印链表();

        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);

        node1.next = node3;
        node3.next = node2;

        System.out.println(Arrays.toString(demo.reversePrint(node1)));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
