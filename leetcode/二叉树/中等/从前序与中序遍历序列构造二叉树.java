/*
 * @Author: kaic
 * @Date: 2022-11-28 14:53:26
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-28 16:47:40
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.Arrays;

import leetcode.二叉树.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 
 * 中等
 * 
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder
 * 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 
 * 
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 
 * 
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class 从前序与中序遍历序列构造二叉树 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 1、如果前序数组为空，说明没有节点，返回null（终止条件）
        if (preorder.length == 0) {
            return null;
        }
        // 2、通过前序数组找到根节点
        int rootValue = preorder[0];
        // 构建根节点
        TreeNode root = new TreeNode(rootValue);
        // 3、如果前序数组size=1，说明`原始二叉树只有一个根节点`或`已经遍历到了叶子节点`
        if (preorder.length == 1) {
            return root;
        }
        // 4、通过第2步找到的根节点，在中序数组中找到该值并作为分割点
        int splitIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                splitIndex = i;
                break;
            }
        }
        // 5、分割中序数组
        int[] newLeftInorder = Arrays.copyOfRange(inorder, 0, splitIndex);
        int[] newRightInorder = Arrays.copyOfRange(inorder, splitIndex + 1, inorder.length);
        // 6、分割前序数组
        int[] newLeftPreorder = Arrays.copyOfRange(preorder, 1, 1 + newLeftInorder.length);
        int[] newRightPreorder = Arrays.copyOfRange(preorder, 1 + newLeftInorder.length, preorder.length);
        // 7、递归左右子树
        root.left = buildTree(newLeftPreorder, newLeftInorder);
        root.right = buildTree(newRightPreorder, newRightInorder);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[] { 3, 9, 20, 15, 7 };
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };

        TreeNode.levelPrint(
                new 从前序与中序遍历序列构造二叉树().buildTree(preorder, inorder));
    }
}
