/*
 * @Author: kaic
 * @Date: 2022-11-28 23:04:11
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 22:55:55
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayList;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 
 * 中等
 * 
 * 
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 
 * 有效 二叉搜索树定义如下：
 * 
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 
 * https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class 验证二叉搜索树 {

    /**
     * 解法一：
     * 
     * 利用二叉搜索树的一个特性：中序遍历节点的数值是单调递增的
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);

        // 判断是否单调递增
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) < result.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 中序遍历
     */
    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    // 当前已经遍历过的节点的最大值
    // private Integer maxValue = null;
    // 当前遍历的节点的前一个节点
    private TreeNode preNode = null;

    /**
     * 解法二：
     * 
     * 递归
     * 
     * 【注意】
     * 不能单纯的比较左节点小于中间节点，右节点大于中间节点。
     * 要比较的是 左子树所有节点小于中间节点，右子树所有节点大于中间节点。
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            // 空树是任意树，它可以是二叉搜索树，也可以是平衡树， 也可以是完全二叉树满二叉树等等等
            return true;
        }

        // 左
        boolean left = isValidBST2(root.left);

        // 中 方法1
        // if (maxValue == null || root.val > maxValue) {
        // // 符合递增要求的节点，更新maxValue并继续往后遍历
        // maxValue = root.val;
        // } else {
        // // 不符合递增要求，说明不是二叉搜索树
        // return false;
        // }

        // 中 方法2
        if (preNode == null) {
            // 初始化preNode
            preNode = root;
        } else if (root.val > preNode.val) {
            // 更新符合递增要求的preNode
            preNode = root;
        } else {
            // 不符合递增要求，说明不是二叉搜索树
            return false;
        }

        // 右
        boolean right = isValidBST2(root.right);

        return left && right;
    }

    public static void main(String[] args) {
        System.out.println(new 验证二叉搜索树().isValidBST2(TreeNode.demo(new Integer[] { 2, 1, 3 })));
    }
}
