/*
 * @Author: kaic
 * @Date: 2022-11-26 09:21:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:24:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 111. 二叉树的最小深度
 * 
 * 简单
 * 
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 */
public class 二叉树的最小深度 {

    /**
     * 层序遍历（广度优先搜索）
     */
    public static int minDepth(TreeNode root) {
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

            depth++;

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

                if (left == null && right == null) {
                    return depth;
                }
            }
        }

        return depth;
    }

    /**
     * 递归（深度优先搜索）
     */
    public static int minDepth2(TreeNode root) {
        // 结束条件1：
        // 当root=null时，表示空节点，此时高度为0
        if (root == null) {
            return 0;
        }
        // 结束条件2：
        // 当root的左右子节点都为空时，表示为叶子节点，此时高度为1
        if (root.left == null && root.right == null) {
            return 1;
        }

        // 计算左右最小深度
        int l = minDepth(root.left);
        int r = minDepth(root.right);

        // 当有一边为空时，返回不为空的孩子节点的最小深度
        if (root.left == null || root.right == null) {
            // 当其中一边为空时，那么这一边必定返回0（结束条件1），所有这里可以直接返回l+r+1（结束条件2其实可以合并在这里）
            return l + r + 1;
        } else {
            // 左右两边都不为空时，比较返回两边最小的深度
            return Math.min(l, r) + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(minDepth2(TreeNode.demo()));
    }
}
