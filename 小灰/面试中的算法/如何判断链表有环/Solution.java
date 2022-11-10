/*
 * @Author: kaic
 * @Date: 2022-11-10 12:54:39
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 17:05:26
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.如何判断链表有环;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 如何判断链表有环
 */
public class Solution {

    /**
     * 两次遍历 解法
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static boolean isCycle1(Node head) {
        if (head == null) {
            return false;
        }

        boolean result = false;

        // 开始遍历
        Node temp = head;
        outer: while (temp != null) {
            // 内循环从头开始遍历到新节点的上一个节点
            Node compare = head;
            while (compare != null && compare.next != temp) { // 遍历到新节点的前一个为止 compare.next != temp
                if (compare == temp) {
                    // 如果链表有环，则可以从头节点遍历到新节点
                    // 否则永远不可能遍历到新节点
                    result = true;

                    // 直接退出外循环
                    break outer;
                }

                // 继续内循环
                compare = compare.next;
            }

            // 退出外循环
            // if (result) {
            // break;
            // }

            // 继续遍历
            temp = temp.next;
        }

        return result;
    }

    /**
     * HashSet 解法
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static boolean isCycle2(Node head) {
        if (head == null) {
            return false;
        }

        boolean result = false;

        // 创建一个Set，用来保存遍历过的节点
        HashSet<Integer> set = new HashSet<>();

        // 开始遍历链表
        Node temp = head;
        while (temp != null) {
            if (set.contains(temp.data)) {
                // 已经遍历过该节点，说明有环
                // 并且当前遍历到的temp节点就是链表环的起点
                result = true;
                break;
            }

            // 保存这次遍历的节点
            set.add(temp.data);

            // 继续遍历
            temp = temp.next;
        }

        return result;
    }

    /**
     * 双指针（快慢指针） 解法
     * 
     * 快指针每次向前移动2个位置
     * 慢指针每次向前移动1个位置
     * 
     * 
     * 环形跑道一样，一个人跑的快一个人跑的慢，那么必定会有一个时刻两人相遇
     * 
     * 【注意：首次相遇的点，不一定就是入环点，可能是环中的任意一个节点，所以不同通过此来判断环的入环口】
     * 
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static boolean isCycle3(Node head) {
        boolean result = false;

        // 创建两个快慢指针
        Node fast = head;
        Node slow = head;

        // 只需要判断fast存在即可（fast存在，那么slow是必定存在的）
        while (fast != null && fast.next != null) {
            // 开始移动
            fast = fast.next.next; // 快指针移动2个位置
            slow = slow.next; // 慢指针移动1个位置

            // 开始判断是否相遇
            if (slow == fast) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * 求环长(借助 HashMap 的解法)
     */
    public static int cycleLength1(Node head) {
        // 存放节点出现的次数
        Map<Node, Integer> map = new HashMap<Node, Integer>();

        // 环长
        int length = 0;

        Node temp = head;

        while (temp != null) {
            if (map.containsKey(temp)) {
                // 遍历次数+1
                map.put(temp, map.get(temp) + 1);
            } else {
                // 首次遍历
                map.put(temp, 1);
            }

            // 遍历了2次
            if (map.get(temp) == 2) {
                // 第二次遍历某个节点，开始计算环的长度
                length++;
            }

            // 遍历了3次
            if (map.get(temp) == 3) {
                // 第三次遍历某个节点，环长度计算完成
                break;

            }

            // 继续遍历
            temp = temp.next;
        }

        // 返回长度
        return length;
    }

    /**
     * 求环长(借助 双指针[快慢指针] 的解法)
     */
    public static int cycleLength2(Node head) {
        // 创建两个快慢指针
        Node fast = head;
        Node slow = head;

        boolean meet = false;
        int length = 0;

        // 只需要判断fast存在即可（fast存在，那么slow是必定存在的）
        while (fast != null && fast.next != null) {
            // 开始移动
            fast = fast.next.next; // 快指针移动2个位置
            slow = slow.next; // 慢指针移动1个位置

            // 开始判断是否相遇
            if (slow == fast) {
                // 相遇了
                meet = true;
                // length == 0 时表示第一次相遇
                if (length != 0) {
                    // 二次相遇
                    // 长度计算完毕（也就是慢指针走的次数）
                    break;
                }
            }

            if (meet) {
                // 已经相遇过了，继续向前走，长度+1
                length++;
            }
        }

        // 环长就是第二次相遇时，慢指针走的步数
        return length;
    }

    /**
     * 判断入环口的位置
     */
    public static Map<Node, Integer> cycleEntry(Node head) {
        // 创建两个快慢指针
        Node fast = head;
        Node slow = head;

        boolean meet = false;

        // 只需要判断fast存在即可（fast存在，那么slow是必定存在的）
        while (fast != null && fast.next != null) {
            // 开始移动
            fast = fast.next.next; // 快指针移动2个位置
            slow = slow.next; // 慢指针移动1个位置

            // 开始判断是否相遇
            if (slow == fast) {
                meet = true;
                break;
            }
        }

        if (meet) {
            // 存在环，开始计算入环口的位置

            // slow回到原点
            // fast位置不变
            slow = head;

            // 入口长度
            int length = 0;

            // 重合后的节点就是环入口
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;

                length++;
            }

            return Map.of(slow, length);
        } else {
            return null;
        }
    }

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "data: " + data + " " + "next-data: " + next.data; // null exception!
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        Node node6 = new Node(8);
        Node node7 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        // 环链表
        node7.next = node4;
        // node7.next = null;

        System.out.println("是否有环1：" + isCycle1(node1));
        System.out.println("是否有环2：" + isCycle2(node1));
        System.out.println("是否有环3：" + isCycle3(node1));

        System.out.println("环长度1：" + cycleLength1(node1));
        System.out.println("环长度2：" + cycleLength2(node1));

        Map<Node, Integer> map = cycleEntry(node1);
        System.out.println("入环口：" + map.keySet() + "，距离：" + map.values());
    }
}
