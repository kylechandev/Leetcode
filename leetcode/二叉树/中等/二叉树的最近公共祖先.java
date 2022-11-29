/*
 * @Author: kaic
 * @Date: 2022-11-29 14:28:31
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 20:38:17
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * 
 * 中等
 * 
 * 
 * 二叉树的最近公共祖先给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）”
 * 
 * 
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class 二叉树的最近公共祖先 {

    /**
     * 自底向上查找（这个过程就是回溯，可以通过`后序遍历-左右中`来实现）
     * 
     * @param root 根节点
     * @param p    节点p
     * @param q    节点q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            // 空节点，返回null
            return null;
        }
        if (root == p || root == q) {
            // 遍历到的子节点是目标节点p或者q，返回这个有效节点，否则继续向下遍历
            return root;
        }

        // 后续遍历过程

        // 继续向下遍历左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q); // 左
        // 继续向下遍历右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q); // 右

        // 中
        if (left != null && right != null) {
            // 同时找到了p和q节点
            // 回溯p和q的root节点上去
            return root;
        } else if (left != null && right == null) {
            // 在左子树中找到了其中一个目标值
            // 回溯left节点上去
            return left;
        } else if (left == null && right != null) {
            // 在右子树中找到了其中一个目标值
            // 回溯right节点上去
            return right;
        } else {
            // 没有找到p或者q
            return null;
        }
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.demo(
                new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 });
        TreeNode.levelPrint(
                new 二叉树的最近公共祖先().lowestCommonAncestor(
                        tree,
                        tree.searchNode(5),
                        tree.searchNode(1)));
    }
}
