/*
 * @Author: kaic
 * @Date: 2022-11-08 11:27:55
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:23:45
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.遍历;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 102. 二叉树的层序遍历
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class 层序 {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 最终结果
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // 创建一个列表（存放一行的节点）
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        // 先入队列根节点
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            // 当前行的长度
            int lineLength = queue.size();

            // 表示每行
            List<Integer> line = new ArrayList<>();

            // 读取这一行的所有元素
            for (int i = 0; i < lineLength; i++) {
                TreeNode treeNode = queue.poll();
                line.add(treeNode.val);

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

            // 添加这一行的数据
            result.add(line);
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = levelOrder(TreeNode.demo());
        System.out.println(result);
    }
}
