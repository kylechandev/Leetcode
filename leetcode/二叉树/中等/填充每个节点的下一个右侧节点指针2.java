/*
 * @Author: kaic
 * @Date: 2023-04-30 14:22:22
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-30 14:29:08
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针2
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class 填充每个节点的下一个右侧节点指针2 {

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
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int lineLength = queue.size();

            while (lineLength > 0) {
                Node node = queue.poll();

                if (lineLength == 1) {
                    // 最后一个
                    node.next = null;
                } else {
                    // 指向这一层的 下一个节点
                    node.next = queue.peek();
                }

                // 继续左右子节点
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
        填充每个节点的下一个右侧节点指针2 demo = new 填充每个节点的下一个右侧节点指针2();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;

        demo.connect(node1);
    }
}
