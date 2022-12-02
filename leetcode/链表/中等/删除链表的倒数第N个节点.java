/*
 * @Author: kaic
 * @Date: 2022-12-02 16:09:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 16:58:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.中等;

import leetcode.链表.ListNode;
import leetcode.链表.简单.反转链表;

/**
 * 19. 删除链表的倒数第N个节点
 * 
 * 中等
 * 
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 
 * 
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class 删除链表的倒数第N个节点 {

    /**
     * 1、先反转
     * 2、再删除第n个节点
     * 3、再反转回去
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = 反转链表.reverseList3(head);

        // 删除第n个
        ListNode dummyHead = new ListNode(-1, node);
        ListNode cur = dummyHead;
        for (int i = 0; i < n - 1; i++) {
            if (cur != null) {
                cur = cur.next;
            }
        }

        if (cur != null) {
            if (cur.next != null) {
                cur.next = cur.next.next;
            } else {
                cur.next = null;
            }
        }

        return 反转链表.reverseList3(dummyHead.next);
    }

    /**
     * 双指针
     * 
     * 如果要删除倒数第n个节点，让fast移动n步，然后让fast和slow同时移动，直到fast指向链表末尾。删掉slow所指向的下一个节点就可以了。
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode slow = dummyHead;
        ListNode fast = dummyHead;

        // 1、让fast移动n+1步，以便slow指向要删除的节点的前一个节点
        for (int i = 0; i < n + 1; i++) {
            if (fast != null) {
                fast = fast.next;
            }
        }

        // 2、然后让fast和slow同时移动，直到fast指向链表末尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 3、删掉slow所指向的下一个节点
        slow.next = slow.next.next;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.demo(new int[] { 1, 2 });
        int n = 2;

        ListNode.printListNode(new 删除链表的倒数第N个节点().removeNthFromEnd2(head, n));
    }
}
