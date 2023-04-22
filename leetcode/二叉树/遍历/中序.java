/*
 * @Author: kaic
 * @Date: 2022-11-14 20:56:17
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-20 20:03:05
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.二叉树.TreeNode;

/**
 * 94. 二叉树的中序遍历 - 「深度优先搜索DFS」
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class 中序 {

    /**
     * 递归解法
     */
    public static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    /**
     * 迭代解法 - 「深度优先搜索DFS」
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = root;

        while (!stack.isEmpty() || treeNode != null) {
            // 先遍历到最深的左节点
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            // 遍历中节点
            treeNode = stack.pop();
            result.add(treeNode.val);

            // 遍历右节点
            // 精髓：当right== nullptr时使得下一次跳过往左走的方向（也就是第二个while，也是第一个while中`treeNode!=null`的作用）。继续出栈往右走。
            treeNode = treeNode.right;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversal(TreeNode.demo(), result);
        System.out.println(result);

        System.out.println(inorderTraversal(TreeNode.demo()));
    }
}
