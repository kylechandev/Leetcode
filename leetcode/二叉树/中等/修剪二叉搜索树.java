/*
 * @Author: kaic
 * @Date: 2022-11-30 10:19:01
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-30 11:08:43
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 669. 修剪二叉搜索树
 * 
 * 中等
 * 
 * 
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树
 * 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 * 
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 * 
 * 
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 */
public class 修剪二叉搜索树 {

    /**
     * @param root 根节点
     * @param low  保留值最小值
     * @param high 保留值最大值
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            // 寻找符合区间[low, high]的节点（因为root已经比low小了，需要找大于low的节点，所以根据二叉搜索树的特性，需要到右子节点去找）
            TreeNode right = trimBST(root.right, low, high);
            return right;
        } else if (root.val > high) {
            // 寻找符合区间[low, high]的节点（因为root已经比high大了，需要找小于high的节点，所以根据二叉搜索树的特性，需要到左子节点去找）
            TreeNode left = trimBST(root.left, low, high);
            return left;
        } else {
            // 符合区间[low, high]的节点，继续去判断它的左右子节点是否符合要求
            // root->left接入符合条件的左孩子
            root.left = trimBST(root.left, low, high);
            // root->right接入符合条件的右孩子
            root.right = trimBST(root.right, low, high);
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.demo(new Integer[] { 1, 0, 2 });
        int low = 1;
        int high = 2;
        TreeNode.levelPrint(new 修剪二叉搜索树().trimBST(root, low, high));
    }
}
