/*
 * @Author: kaic
 * @Date: 2022-11-26 11:16:12
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-20 20:42:45
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 101. 对称二叉树 - 「广度优先搜索BFS」
 * 
 * 简单
 * 
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class 对称二叉树 {

    /**
     * 迭代法
     * 
     * 使用双端队列
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>(); // 为了支持null入队列，使用LinkedList而不是ArrayDeque

        queue.offerFirst(root.left);
        queue.offerLast(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollLast();

            if (left == null && right == null) {
                continue;
            }

            // left == null || right == null -> 左右子树其中一个为null，直接不对称
            // left.val != right.val -> 左右子树都存在，但值不同，所以不匹配
            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            // 镜像对称：
            // 左子树的左节点和右子树的右节点比较
            // 左子树的右节点和右子树的左节点比较
            queue.offerFirst(left.left);
            queue.offerFirst(left.right);
            queue.offerLast(right.right);
            queue.offerLast(right.left);
        }

        return true;
    }

    /**
     * 迭代法
     * 
     * 使用普通队列
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>(); // 为了支持null入队列，使用LinkedList而不是ArrayDeque

        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            // 镜像对称：
            // 左子树的左节点和右子树的右节点比较
            // 左子树的右节点和右子树的左节点比较
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    /**
     * 递归法
     */
    public static boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root.left, root.right);
    }

    private static boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            // 左右子树都为空，对称，返回true
            return true;
        } else if (left == null || right == null || left.val != right.val) {
            // left == null || right == null -> 左右子树其中一个为null，直接不对称
            // left.val != right.val -> 左右子树都存在，但值不同，所以不匹配
            return false;
        } else {
            // 上一个`else if`已经判断过值不相等了，所以这里可以不用再判断了
            // 继续比较左右子树的 外侧(left.left, right.right) 和 内侧(left.right, right.left) 是否对称
            // KEY: 左右子树匹配的点在第一个`if`（以只有一根节点的二叉树代入思考，我们其实根本没处理到判断左右子节点的值是否相等）
            return check(left.left, right.right) && check(left.right, right.left);
        }
    }

    public static void main(String[] args) {
        System.out.println(isSymmetric3(TreeNode.demo()));
        System.out.println(isSymmetric3(TreeNode.symmetric()));
    }
}
