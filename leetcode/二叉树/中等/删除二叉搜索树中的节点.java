/*
 * @Author: kaic
 * @Date: 2022-11-29 21:22:46
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-30 10:17:52
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 * 
 * 中等
 * 
 * 
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key
 * 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 
 * 一般来说，删除节点可分为两个步骤：
 * 
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 
 * 
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 */
public class 删除二叉搜索树中的节点 {

    /**
     * 递归解法
     * 
     * @return 通过递归返回值来删除节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // 终止条件
        if (root == null) {
            return null;
        }

        // 当key>root.val，从右子树删除
        // 当key<root.val，从左子树删除
        // 当key=root.val，从当前节点删除：
        // 1、当root没有左右子节点，直接删除；
        // 2、当root没有左子节点或右子节点，则让右子节点或左子节点顶替它的位置
        // 3、当root左右子节点都存在时，则让左子树转移到右子树的最左节点的左子树上，然后右子树顶替位置
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            // 如果root.right没有被删除（root=null），那么递归返回时会把原节点再次返回回去
            return root; // 这里return root; 可以不写，因为函数最后有统一 return
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else {
            if (root.left == null && root.right == null) {
                // 直接删除
                root = null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 右子节点
                TreeNode right = root.right;
                while (right.left != null) {
                    // 遍历到右子节点的最左侧节点
                    right = right.left;
                }
                // 把左子树挂到右子节点的最左侧左子节点上
                right.left = root.left;
                // 让右子节点代替删除的位置
                return root.right;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(
                new 删除二叉搜索树中的节点().deleteNode(
                        TreeNode.demo(new Integer[] { 5, 3, 6, 2, 4, null, 7 }), 3));
    }
}
