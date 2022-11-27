/*
 * @Author: kaic
 * @Date: 2022-11-27 11:00:14
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-27 15:25:57
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import leetcode.二叉树.TreeNode;

/**
 * 路径总和
 * 
 * 简单
 * 
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 
 * https://leetcode.cn/problems/path-sum/
 */
public class 路径总和 {

    static boolean result = false;

    /**
     * 递归解法（慢，请查看优化解法）
     * 
     * @param root      根节点
     * @param targetSum 目标路径总和
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        List<Integer> path = new ArrayList<>();
        result = false;
        trasverval(root, path, targetSum);

        return result;
    }

    private static void trasverval(TreeNode root, List<Integer> path, int targetSum) {
        path.add(root.val);

        if (root.left == null && root.right == null) {
            // 找到了叶子节点，一条路径寻找完毕
            if (sumIntList(path) == targetSum) {
                result = true;
                printIntList(path);
                return;
            }
        }

        if (root.left != null) {
            trasverval(root.left, path, targetSum);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            trasverval(root.right, path, targetSum);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 递归（优化解法）
     * 
     * 【计数器思想】
     * 
     * 递归函数需要有返回值，当需要符合条件的路径后及时返回跳出递归，避免后面多余的递归
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        targetSum -= root.val;

        if (root.left == null && root.right == null) {
            // 找到了叶子节点
            return targetSum == 0; // 一旦targetSum=0了，说明找到符合条件的路径了
        }

        // 确定单层递归的逻辑
        if (root.left != null) {
            boolean left = hasPathSum2(root.left, targetSum);
            if (left) {
                return true;
            }
        }
        if (root.right != null) {
            boolean right = hasPathSum2(root.right, targetSum);
            if (right) {
                return true;
            }
        }

        return false;
    }

    /**
     * 广度优先搜索
     * 
     * 遍历到每一个节点时，记录从根节点到此节点的路径
     */
    public static boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(targetSum);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int tempSum = queueSum.poll();

            if (node.left == null && node.right == null) {
                // 遍历到叶子节点了，一条路径搜索完成
                if (tempSum == targetSum) {
                    // 找到了符合要求的路径
                    return true;
                } else {
                    // 继续寻找
                    continue;
                }
            }

            // 继续遍历左右子节点
            if (node.left != null) {
                queueNode.offer(node.left);
                queueSum.offer(tempSum + node.left.val);
            }
            if (node.right != null) {
                queueNode.offer(node.right);
                queueSum.offer(tempSum + node.right.val);
            }
        }

        return false;
    }

    private static int sumIntList(List<Integer> list) {
        return (int) list.stream().collect(Collectors.summarizingInt(value -> value)).getSum();
    }

    private static void printIntList(List<Integer> list) {
        String string = list.stream().map(String::valueOf).collect(Collectors.joining("->"));
        System.out.println(string);
    }

    public static void main(String[] args) {
        System.out.println(
                hasPathSum3(TreeNode.demo(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 }), 22));
    }
}
