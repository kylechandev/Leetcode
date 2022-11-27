/*
 * @Author: kaic
 * @Date: 2022-11-27 09:49:15
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-27 10:58:36
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 513. 找树左下角的值
 * 
 * 中等
 * 
 * 
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 【注意：`最底层 最左边`不一定是左节点，右节点也可以，只要是最后一层的最左边一个节点就行】
 * 
 * 假设二叉树中至少有一个节点。
 * 
 * 
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 */
public class 找树左下角的值 {

    /**
     * 层序遍历，返回最底层第一个值
     */
    public static int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }

        Integer lineFirst = null;

        while (!queue.isEmpty()) {
            int size = queue.size();
            lineFirst = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (lineFirst == null) {
                    lineFirst = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return lineFirst;
    }

    // 记录已遍历的最大深度
    static int maxDepth = 0;
    // 返回的结果
    static int result = 0;

    /**
     * 递归
     */
    public static int findBottomLeftValue2(TreeNode root) {
        maxDepth = 0;
        result = 0;

        traversal(root, 0);

        return result;
    }

    /**
     * 确定函数的返回值和参数
     * 
     * @param root  当前节点
     * @param depth 当前节点的深度
     */
    static void traversal(TreeNode root, int depth) {
        // 确定终止条件
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            // 找到叶子节点
            if (depth > maxDepth) { // 这里深度的判断还可以作为【某一层第一次访问的节点】的判断条件
                // 更新为更大深度首次访问的节点值
                maxDepth = depth;
                result = root.val;
            }
        }

        // 确定单层递归的逻辑
        // 从左往右遍历
        if (root.left != null) {
            traversal(root.left, depth + 1);
        }
        if (root.right != null) {
            traversal(root.right, depth + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(findBottomLeftValue2(TreeNode.demo(new Integer[] { 1, 2, 3 })));
    }
}
