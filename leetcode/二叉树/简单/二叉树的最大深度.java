/*
 * @Author: kaic
 * @Date: 2022-11-25 16:26:34
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-23 14:49:33
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 104. 二叉树的最大深度
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class 二叉树的最大深度 {

    /**
     * 递归法（深度优先搜索）
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 层序遍历（广度优先搜索）
     * 
     * 层数就是深度
     */
    public static int maxDepth2(TreeNode root) {
        // 创建一个列表（存放一行的节点）
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        // 先入队列根节点
        if (root != null) {
            queue.offer(root);
        }

        int depth = 0;

        while (!queue.isEmpty()) {
            // 当前行的长度
            int lineLength = queue.size();

            // 读取这一行的所有元素
            for (int i = 0; i < lineLength; i++) {
                TreeNode treeNode = queue.poll();

                // 开始入队列的左右子节点
                TreeNode left = treeNode.left;
                if (left != null) {
                    queue.offer(left);
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    queue.offer(right);
                }
            }

            depth++;
        }

        return depth;
    }

    public static void main(String[] args) {
        System.out.println(maxDepth2(TreeNode.demo()));
    }
}
