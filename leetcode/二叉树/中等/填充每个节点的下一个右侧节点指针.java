/*
 * @Author: kaic
 * @Date: 2022-11-25 15:57:56
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:25:34
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
 */
public class 填充每个节点的下一个右侧节点指针 {

    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public static Node connect(Node root) {
        // 创建一个列表（存放一行的节点）
        Queue<Node> queue = new ArrayDeque<Node>();

        // 先入队列根节点
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            // 当前行的长度
            int lineLength = queue.size();

            while (lineLength > 0) {
                Node node = queue.poll();
                if (lineLength == 1) {
                    // 最后一个节点置为空
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                lineLength--;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        connect(node1);
    }
}
