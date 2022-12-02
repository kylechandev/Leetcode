/*
 * @Author: kaic
 * @Date: 2022-12-02 10:00:57
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 13:38:42
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.简单;

import leetcode.链表.ListNode;

/**
 * 203. 移除链表元素
 * 
 * 简单
 * 
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 
 * 
 * https://leetcode.cn/problems/remove-linked-list-elements/
 */
public class 移除链表元素 {

    public ListNode removeElements(ListNode head, int val) {
        // 移除头部节点
        // while (head != null && head.val == val) {
        // head = head.next;
        // }
        // if (head == null) {
        // return null;
        // }

        // // 移除中间节点
        // ListNode cur = head;
        // while (cur != null) {
        // while (cur.next != null && cur.next.val == val) { // 使用while删除连续出现的val
        // cur.next = cur.next.next;
        // }
        // cur = cur.next;
        // }
        // return head;

        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        while (cur != null) {
            if (cur.val == val) {
                // 删除当前节点
                pre.next = cur.next;
            } else {
                // 更新pre节点
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // ListNode head = ListNode.demo(new int[] { 1, 2, 6, 3, 4, 5, 6 });
        ListNode head = ListNode.demo(new int[] { 7, 7, 7, 7 });
        ListNode.printListNode(head);

        ListNode remove = new 移除链表元素().removeElements(head, 7);
        ListNode.printListNode(remove);
    }
}
