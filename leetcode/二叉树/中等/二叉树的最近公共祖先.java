/*
 * @Author: kaic
 * @Date: 2022-11-29 14:28:31
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 20:01:21
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return root;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.demo(
                new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 });
    }
}
