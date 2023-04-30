/*
 * @Author: kaic
 * @Date: 2023-04-28 10:28:35
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-30 14:09:17
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 114. 二叉树展开为链表
 * 
 * 中等
 * 
 * 
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 
 * 
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 */
public class 二叉树展开为链表 {

    /**
     * 最直白的思路：先 先序 遍历记录，然后直接创建新的二叉树
     */
    public void flattenX(TreeNode root) {

    }

    /**
     * 按照 先序遍历 方式展开
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 找 当前根节点 的 左子树 的 右叶子节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 把 当前根节点 的 右子树 接到刚刚找到的 叶子节点上
                pre.right = root.right;
                // 把 右子树 接到原本的 左子树上
                root.right = root.left;
                // 置空左子树
                root.left = null;
            }

            // 继续下一个节点
            root = root.right;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.demo(new Integer[] { 1, 2, 5, 3, 4, null, 6 });

        二叉树展开为链表 demo = new 二叉树展开为链表();
        demo.flatten(treeNode);

        TreeNode.levelPrint(treeNode);
    }
}
