/*
 * @Author: kaic
 * @Date: 2022-11-29 20:36:16
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 21:07:06
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 
 * 中等
 * 
 * 
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
 * 
 * 
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class 二叉搜索树的最近公共祖先 {

    /**
     * 递归解法
     * 
     * @param root 根节点
     * @param p    节点p
     * @param q    节点q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            // 遍历左侧
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            // 遍历右侧
            return lowestCommonAncestor(root.right, p, q);
        } else {
            // 当前节点值在p和q之间，根据二叉搜索树的特性，说明要找的节点就是这个节点
            return root;
        }
    }

    /**
     * 迭代解法
     * 
     * @param root 根节点
     * @param p    节点p
     * @param q    节点q
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val && node.val > q.val) {
                // 遍历左侧
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                // 遍历右侧
                node = node.right;
            } else {
                // 当前节点值在p和q之间，根据二叉搜索树的特性，说明要找的节点就是这个节点
                return node;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.demo(
                new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 });
        TreeNode.levelPrint(
                new 二叉搜索树的最近公共祖先().lowestCommonAncestor2(
                        tree,
                        tree.searchNode(2),
                        tree.searchNode(4)));
    }
}
