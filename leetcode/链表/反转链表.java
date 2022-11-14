/*
 * @Author: kaic
 * @Date: 2022-11-13 21:08:31
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-14 09:39:09
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表;

import java.util.Stack;

/**
 * 反转链表
 * 
 * 简单
 * 
 * 
 * 【对于递归】：
 * 处理的技巧是：不要跳进递归，而是利用明确的定义来实现算法逻辑。
 * 处理看起来比较困难的问题，可以尝试化整为零，把一些简单的解法进行修改，解决困难的问题。
 * 
 * 
 * 值得一提的是，递归操作链表并不高效。
 * 和迭代解法相比，虽然时间复杂度都是 O(N)，但是迭代解法的空间复杂度是 O(1)，而递归解法需要堆栈，空间复杂度是O(N)。
 * 所以考虑效率的话还是使用迭代算法更好。
 * 
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

        ListNode(int val) {
            this.val = val;
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
     * 
     * 可以参考的思路讲解，和原先自己思考的大致相仿
     * http://www.justdojava.com/2020/03/29/recursiveLinkList/
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
        // 这个递归解法就相当于把问题拆分解决，然后一步步解决完整问题。每次递归 归 的时候都是反转最后一个还未反转的节点

        // 返回分解链表部分的反转后的头节点
        return last;
    }

    // 后驱节点
    private static ListNode successor = null;

    /**
     * 递归扩展 - 反转前N个节点
     * 
     * @param n 前N个节点
     */
    public static ListNode reverseListN(ListNode head, int n) {
        // 1、定义递归函数
        if (head == null) {
            return null;
        }

        // 2、确定递归结束条件
        if (n == 1 || head.next == null) {
            // n == 1 前N个节点递归完成
            // head.next == null 防止n大于链表长度
            successor = head.next; // 递归完成，记录第 n + 1 个节点，以便让递归`归`时让head.hext指向第n+1个节点
            return head;
        }

        // 3、找出递归函数等价关系式：

        // 对于head来说，反转前n个节点
        // 对于head.next来说，就是反转前n-1个节点
        ListNode last = reverseListN(head.next, n - 1);

        // 递归的归
        // 反转链表操作
        head.next.next = head;
        head.next = successor;

        return last;
    }

    /**
     * 反转链表中间一部分
     * 
     * @param left  开始反转的节点
     * @param right 结束反转的节点
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 1、定义递归函数
        if (head == null) {
            return null;
        }

        // 2、确定递归结束条件
        if (left == 1) {
            // 如果开始节点left=1，那么就相当于反转链表开头的N个节点
            return reverseListN(head, right);
        }

        // 3、找出递归函数等价关系式：

        // 对于head节点来说，反转left到right之间的节点
        // 对于head.next节点来说，就是反转left-1到right-1之间的节点
        head.next = reverseBetween(head.next, left - 1, right - 1);

        return head;
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
        ListNode reverse = reverseBetween(node1, 2, 4);

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
