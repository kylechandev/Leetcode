/*
 * @Author: kaic
 * @Date: 2022-11-25 15:39:43
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:25:47
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 429. N叉树的层序遍历
 * 
 * 中等
 * 
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * 
 * https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
 */
public class N叉树的层序遍历 {

    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    /**
     * 层序遍历思想
     */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<Node> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                line.add(node.val);

                // 继续添加子节点
                if (node.children != null && !node.children.isEmpty()) {
                    node.children.forEach((e) -> {
                        queue.offer(e);
                    });
                }
            }
            result.add(line);
        }

        return result;
    }

    public static void main(String[] args) {
        // 层序构造N叉树
        // Integer[] arrays = new Integer[] { 1, null, 3, 2, 4, null, 5, 6 };
        Node node = new Node(3, Arrays.asList(new Node(5), new Node(6)));
        Node root = new Node(1, Arrays.asList(node, new Node(2), new Node(4)));

        System.out.println(levelOrder(root));
    }
}
