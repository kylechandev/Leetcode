/*
 * @Author: kaic
 * @Date: 2022-12-02 21:26:05
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 21:57:22
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.链表.中等;

import leetcode.链表.ListNode;

/**
 * 142. 环形链表 II
 * 
 * 中等
 * 
 * 
 * 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos
 * 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 
 * 
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class 环形链表2 {

    /**
     * 判断是否有环 - 双指针（快慢指针）
     */
    private boolean containsCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * 双指针（快慢指针）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode detectCycle(ListNode head) {
        boolean contiansCycle = false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                contiansCycle = true;
                break;
            }
        }

        System.out.println(contiansCycle);

        System.out.println("fast: " + fast.val);
        System.out.println("slow: " + slow.val);

        if (contiansCycle) {
            fast = head; // 重置fast或者slow都行
            while (fast != null && slow != null) {
                if (fast == slow) {
                    System.out.println("result: " + fast.val);
                    return fast;
                }

                fast = fast.next;
                slow = slow.next;
            }
        }

        return null;
    }

    /**
     * 借助hashmap
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode detectCycle1(ListNode head) {
        // 遍历head，先判断hashmap中是否存在当前节点，若不存在则添加到hashmap，若存在表示为环入口
        return null;
    }

    public static void main(String[] args) {
        // 测试用例1
        // ListNode n1 = new ListNode(3);
        // ListNode n2 = new ListNode(2);
        // ListNode n3 = new ListNode(0);
        // ListNode n4 = new ListNode(-4);
        // n1.next = n2;
        // n2.next = n3;
        // n3.next = n4;
        // n4.next = n2;

        // 测试用例2
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = n1;

        System.out.println(new 环形链表2().containsCycle(n1));

        new 环形链表2().detectCycle(n1);
        // ListNode.printListNode(result);
    }
}
