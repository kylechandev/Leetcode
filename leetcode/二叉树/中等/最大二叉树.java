/*
 * @Author: kaic
 * @Date: 2022-11-28 16:58:34
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-28 17:08:10
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.Arrays;

import leetcode.二叉树.TreeNode;

/**
 * 654. 最大二叉树
 * 
 * 中等
 * 
 * 
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * 
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 * 
 * https://leetcode.cn/problems/maximum-binary-tree/
 */
public class 最大二叉树 {

    /**
     * 递归解法
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 1、如果当前数组size=0，表示遍历完成，返回null
        if (nums.length == 0) {
            return null;
        }

        // 2、计算当前数组中的最大值
        int maxValue = nums[0];
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
        }
        // 构建节点
        TreeNode root = new TreeNode(maxValue);

        // 3、如果当前数组size=1，表示为一侧最后一个叶子节点
        if (nums.length == 1) {
            return root;
        }

        // 4、以最大值位置作为数组的分割点，分为左右两部分，分别表示左子树和右子树
        int[] left = Arrays.copyOfRange(nums, 0, maxIndex);
        int[] right = Arrays.copyOfRange(nums, maxIndex + 1, nums.length);

        // 5、递归的构建左右子树
        root.left = constructMaximumBinaryTree(left);
        root.right = constructMaximumBinaryTree(right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(
                new 最大二叉树().constructMaximumBinaryTree(new int[] { 3, 2, 1 }));
    }
}
