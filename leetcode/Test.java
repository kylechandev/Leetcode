/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-25 10:08:02
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 临时算法
 */
public class Test {

    /**
     * 先序遍历
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // 根左右
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);

            TreeNode right = treeNode.right;
            if (right != null) {
                stack.push(right);
            }
            TreeNode left = treeNode.left;
            if (left != null) {
                stack.push(left);
            }
        }

        return result;
    }

    /**
     * 中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        // 左根右

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            treeNode = stack.pop();
            result.add(treeNode.val);
            treeNode = treeNode.right;
        }

        return result;
    }

    /**
     * 层序遍历
     */
    public static List<List<Integer>> levelorderTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> line = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                line.add(treeNode.val);

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }

            result.add(line);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(levelorderTraversal(TreeNode.demo()));
    }
}
