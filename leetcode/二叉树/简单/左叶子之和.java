/*
 * @Author: kaic
 * @Date: 2022-11-26 23:02:14
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-27 09:48:00
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 404. 左叶子之和
 * 
 * 简单
 * 
 * 给定二叉树的根节点 root ，返回所有`左叶子`之和。
 * 
 * https://leetcode.cn/problems/sum-of-left-leaves/
 */
public class 左叶子之和 {

    /**
     * 递归解法
     * 
     * @param root 树的根节点
     * @return 左子叶子节点数值之和
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        // 终止条件
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            // 只有当前遍历的节点是父节点，才能判断其子节点是不是左叶子
            // 所以如果当前遍历的节点是叶子节点，那其左叶子也必定是0
            return 0;
        }

        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);

        if (root.left != null && root.left.left == null && root.left.right == null) {
            // 找到了左叶子节点：root.left
            leftValue = root.left.val;
        }

        int sum = leftValue + rightValue;

        return sum;
    }

    /**
     * 迭代解法（先序遍历）
     */
    public static int sumOfLeftLeaves2(TreeNode root) {
        int sum = 0;

        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            // 根
            TreeNode node = stack.pop();

            // 判断是否为左叶子节点
            if (node.left != null && node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            }

            // 右
            if (node.right != null) {
                stack.push(node.right);
            }
            // 左
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return sum;
    }

    /**
     * 迭代解法（层序遍历）
     */
    public static int sumOfLeftLeaves3(TreeNode root) {
        int sum = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int lineSize = queue.size();
            for (int i = 0; i < lineSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    // 层序遍历，添加下一层（左）
                    queue.offer(node.left);

                    // 判断下一层的左子节点是否为叶子节点
                    if (node.left.left == null && node.left.right == null) {
                        sum += node.left.val;
                    }
                }

                if (node.right != null) {
                    // 层序遍历，添加下一层（右）
                    queue.offer(node.right);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumOfLeftLeaves3(TreeNode.demo(new Integer[] { 3, 9, 20, null, null, 15, 7 })));
    }
}
