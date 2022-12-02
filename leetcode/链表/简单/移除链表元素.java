/*
 * @Author: kaic
 * @Date: 2022-12-02 10:00:57
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 10:06:58
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
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.demo(new int[] { 1, 2, 6, 3, 4, 5, 6 });
        ListNode.printListNode(head);
    }
}
