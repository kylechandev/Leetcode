/*
 * @Author: kaic
 * @Date: 2022-12-02 13:39:24
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 14:34:29
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.中等;

import leetcode.链表.ListNode;

/**
 * 707. 设计链表
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/design-linked-list/
 */
public class 设计链表 {

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        // linkedList.addAtHead(1);
        // ListNode.printListNode(linkedList.getHead());
        // linkedList.addAtTail(3);
        // ListNode.printListNode(linkedList.getHead());
        linkedList.addAtIndex(1, 0);
        ListNode.printListNode(linkedList.getHead());

        System.out.println(linkedList.get(0));

        // linkedList.deleteAtIndex(0);
        // ListNode.printListNode(linkedList.getHead());

        // System.out.println(linkedList.get(0));
    }
}

class MyLinkedList {

    // size存储链表元素的个数
    public int size = 0;
    // 虚拟头结点
    private ListNode dummyHead = null;

    // 初始化链表
    public MyLinkedList() {
        size = 0;
        dummyHead = new ListNode(-1);
    }

    public ListNode getHead() {
        return dummyHead.next;
    }

    /**
     * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
     */
    public int get(int index) {
        ListNode cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            if (cur != null) {
                cur = cur.next;
            }
        }

        return cur == null ? -1 : cur.val;
    }

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     */
    public void addAtHead(int val) {
        // 创建新节点
        ListNode newNode = new ListNode(val);
        if (dummyHead.next != null) {
            // 后面还有其他的节点
            newNode.next = dummyHead.next; // 先连接上后面的节点
            dummyHead.next = newNode; // 再添加新节点到head
        } else {
            // 唯一一个节点
            dummyHead.next = newNode;
        }

        size++;
    }

    /**
     * 将值为 val 的节点追加到链表的最后一个元素。
     */
    public void addAtTail(int val) {
        if (dummyHead.next == null) {
            addAtHead(val);
        } else {
            ListNode cur = dummyHead.next;
            while (cur != null) {
                if (cur.next == null) {
                    // 遍历到当前最后一个节点
                    cur.next = new ListNode(val);
                    break;
                } else {
                    cur = cur.next;
                }
            }

            size++;
        }
    }

    /**
     * 在链表中的第 index 个节点之前添加值为 val 的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        // 12
        if (index == 0) {
            addAtHead(val);
        } else {
            ListNode cur = dummyHead.next;
            for (int i = 0; i < index - 1; i++) {
                if (cur != null) {
                    cur = cur.next;
                }
            }

            if (cur == null || cur.next == null) {
                addAtTail(val);
            } else {
                // 在中间插入
                ListNode newNode = new ListNode(val);
                newNode.next = cur.next;
                cur.next = newNode;

                size++;
            }
        }
    }

    /**
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     */
    public void deleteAtIndex(int index) {
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            // 找到第index-1个
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
    }
}
