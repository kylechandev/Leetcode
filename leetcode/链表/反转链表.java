/*
 * @Author: kaic
 * @Date: 2022-11-13 21:08:31
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-13 22:33:59
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表;

import java.util.Stack;

/**
 * 反转链表
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class 反转链表 {

    /*
     * Definition for singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 迭代解法 - 借助栈
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param head 头节点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1 -> 2 -> 3 -> 4 -> 5

        // 借助栈把当前节点按顺序压入栈
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 栈顶元素作为反转链表后的头节点
        ListNode reverse = stack.pop();
        // 遍历弹出栈顶元素，形成新的链表
        ListNode curReverse = reverse;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = null; // 「关键」清除原始链表信息，否则会形成循环链表，后续遍历输出的时候直接死循环
            curReverse.next = node;
            curReverse = curReverse.next;
        }

        // 返回反转后的链表头节点
        return reverse;
    }

    /**
     * 迭代解法 - 不借助栈
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param head 头节点
     */
    public static ListNode reverseList2(ListNode head) {
        // 1 -> 2 -> 3 -> 4 -> 5
        ListNode pre = null;
        ListNode current = head;

        // 思想为：直接在原来链表的基础上，把原链表一个节点一个节点的做反转（就像问题分解一样）
        while (current != null) {
            // 先暂存下一个节点的位置
            // 因为马上要更新当前节点的下一个节点重新指向它的前一个节点
            ListNode next = current.next;
            // 让当前节点的后节点重新指向前一个节点
            current.next = pre;

            // 开始新的一轮，位置后移

            // 让前一个节点更新为当前节点
            pre = current;
            // 当前节点继续后移
            current = next;
        }

        return pre;
    }

    /**
     * 递归解法 - 递归的本质就是函数调用栈
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param head 头节点
     */
    public static ListNode reverseList3(ListNode head) {
        // 1、定义递归函数：reverseList2(head)：它的作用就是让节点反转（例如1234，让1和4反转 或者 让2和3反转）

        // 空节点
        if (head == null) {
            return null;
        }

        // 1 -> 2 -> 3 -> 4 -> 5

        // 2、决定递归结束条件：
        if (head.next == null) { // 遍历到最后一个节点了 或者理解为 链表只有一个节点的时候直接返回它自身
            return head;
        }

        // 3、找出递归函数等价关系式：

        // 递归 递 的过程
        ListNode last = reverseList3(head.next); // 接着反转head.next节点，返回反转后的头节点

        // 递归 归 的过程
        head.next.next = head;
        head.next = null;
        // 想一下，如果是只有两个节点的链表：1 -> 2，反转一下就是：2 -> 1
        // 操作步骤即: 1.next.next = 1; 1.next = null
        // 这个递归解法就相当于把问题拆分解决，然后一步步解决完整问题。每次递归 归 的时候都是反转最后一个还未反转的节点，
≠
        // 返回分解链表部分的反转后的头节点
        return last;
    }

    public static void main(String[] args) {
        // 模拟数据 1 2 3 4 5
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // 输出 5 4 3 2 1
        ListNode reverse = reverseList2(node1);

        if (reverse == null) {
            System.out.println("无节点");
        } else {
            // 输出
            while (reverse != null) {
                System.out.print(reverse.val + " ");
                reverse = reverse.next;
            }
        }
    }
}
