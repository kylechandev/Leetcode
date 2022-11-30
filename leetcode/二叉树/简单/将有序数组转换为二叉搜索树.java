/*
 * @Author: kaic
 * @Date: 2022-11-30 11:09:40
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-30 11:19:09
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.Arrays;

import leetcode.二叉树.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 
 * 简单
 * 
 * 
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 
 * 
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 */
public class 将有序数组转换为二叉搜索树 {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length <= 0) {
            return null;
        }

        // 二分
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        root.left = sortedArrayToBST(left);
        int[] right = Arrays.copyOfRange(nums, mid + 1, nums.length);
        root.right = sortedArrayToBST(right);

        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -10, -3, 0, 5, 9 };
        TreeNode.levelPrint(new 将有序数组转换为二叉搜索树().sortedArrayToBST(nums));
    }
}
