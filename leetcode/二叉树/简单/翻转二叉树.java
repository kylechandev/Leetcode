/*
 * @Author: kaic
 * @Date: 2022-11-26 10:30:19
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 11:02:27
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 226. 翻转二叉树
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class 翻转二叉树 {

    /**
     * 利用层序遍历
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                TreeNode left = node.left;
                TreeNode right = node.right;

                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }

                // 翻转左右子节点
                node.left = right;
                node.right = left;
            }
        }

        return root;
    }

    /**
     * 利用递归
     */
    public static TreeNode invertTree2(TreeNode root) {
        // 终止条件
        if (root == null) {
            return null;
        }

        // 使用 前序遍历（根左右） 和 后续遍历都可以（左右根），不能使用中序遍历（左根右），因为会出现左右孩子翻转两次的情况

        // 交换左右子节点
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree2(root.left);
        invertTree2(root.right);

        // 如果使用中序遍历，需要这样操作：
        // invertTree(root->left); // 左
        // swap(root->left, root->right); // 中
        // invertTree(root->left); // 注意 这里依然要遍历左孩子，因为中间节点已经翻转了

        return root;
    }

    /**
     * 利用前序遍历迭代
     */
    public static TreeNode invertTree3(TreeNode root) {
        // 根 - 左 - 右

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(invertTree3(TreeNode.demo()));
    }
}
