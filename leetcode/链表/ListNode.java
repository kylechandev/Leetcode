package leetcode.链表;

/*
 * @Author: kaic
 * @Date: 2022-12-02 10:00:01
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 10:08:24
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

public class ListNode {
    // 结点的值
    public int val;

    // 下一个结点
    public ListNode next;

    // 节点的构造函数(无参)
    public ListNode() {
    }

    // 节点的构造函数(有一个参数)
    public ListNode(int val) {
        this.val = val;
    }

    // 节点的构造函数(有两个参数)
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 构建一个链表
     */
    public static ListNode demo(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        ListNode head = new ListNode(nums[0]);

        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            ListNode node = new ListNode(num);
            cur.next = node;
            cur = node;
        }

        return head;
    }

    /**
     * 打印一个链表
     */
    public static void printListNode(ListNode head) {
        if (head == null) {
            System.out.println("list head node is null");
        } else {
            ListNode cur = head;
            while (cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}
