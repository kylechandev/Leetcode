/*
 * @Author: kaic
 * @Date: 2022-11-29 21:08:35
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 21:21:11
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 * 
 * 中等
 * 
 * 
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 输入数据 保证，新值和原始二叉搜索树中的任意节点值都不同。
 * 
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * 
 * 
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 */
public class 二叉搜索树中的插入操作 {

    /**
     * 递归解法
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // 满足leetcode测试用例：
            // 输入：root=[], val=5
            // 输出：[5]
            // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回
            return new TreeNode(val);
        }

        if (val < root.val) {
            // 插左边
            if (root.left != null) {
                insertIntoBST(root.left, val);
            } else {
                // 插入
                TreeNode node = new TreeNode(val);
                root.left = node;
            }
        } else {
            // 插右边
            if (root.right != null) {
                insertIntoBST(root.right, val);
            } else {
                // 插入
                TreeNode node = new TreeNode(val);
                root.right = node;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(
                new 二叉搜索树中的插入操作().insertIntoBST(
                        TreeNode.demo(new Integer[] { 4, 2, 7, 1, 3 }), 5));
    }
}
