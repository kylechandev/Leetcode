/*
 * @Author: kaic
 * @Date: 2022-12-02 14:40:35
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 16:06:05
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.中等;

import leetcode.链表.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 
 * 中等
 * 
 * 
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 
 * 
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 
 * 输入：head = [1,2,3]
 * 输出：[2,1,3]
 * 
 * 
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class 两两交换链表中的节点 {

    /*
     * 迭代解法
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode cur = dummyHead;

        while (cur.next != null && cur.next.next != null) {
            ListNode next = cur.next;
            ListNode last = cur.next.next.next;

            // 开始交换
            cur.next = cur.next.next;
            cur.next.next = next;
            next.next = last;

            // 准备下一轮交换
            cur = cur.next.next;
        }

        return dummyHead.next;
    }

    /*
     * 递归解法
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            // 需要两位交换，所以需要head以及head.next都不为空
            return head;
        }

        // 执行交换 - 交换head和next
        ListNode next = head.next; // 当前的head.next
        head.next = swapPairs(head.next.next); // 1、head连接到后面交换完成的子链表
        next.next = head; // 2、next连接到head，完成交换

        return next; // head和head.next交换完成后，返回交换后的next节点
    }

    public static void main(String[] args) {
        ListNode head = ListNode.demo(new int[] { 1, 2, 3, 4 });
        ListNode.printListNode(new 两两交换链表中的节点().swapPairs2(head));
    }
}
