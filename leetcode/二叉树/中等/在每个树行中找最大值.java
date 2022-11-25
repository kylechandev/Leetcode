/*
 * @Author: kaic
 * @Date: 2022-11-25 15:53:06
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-25 15:55:50
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 在每个树行中找最大值
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 */
public class 在每个树行中找最大值 {

    /**
     * 层序遍历思想
     */
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 最终结果
        List<Integer> result = new ArrayList<>();

        // 创建一个列表（存放一行的节点）
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        // 先入队列根节点
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 当前行的长度
            int lineLength = queue.size();

            int max = Integer.MIN_VALUE;

            // 读取这一行的所有元素
            for (int i = 0; i < lineLength; i++) {
                TreeNode treeNode = queue.poll();
                max = Math.max(max, treeNode.val);

                TreeNode left = treeNode.left;
                if (left != null) {
                    queue.offer(left);
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    queue.offer(right);
                }
            }

            // 添加这一行的数据
            result.add(max);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(largestValues(TreeNode.demo()));
    }
}
