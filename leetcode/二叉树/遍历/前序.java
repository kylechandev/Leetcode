/*
 * @Author: kaic
 * @Date: 2022-11-14 15:17:28
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-14 20:53:24
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.二叉树.TreeNode;

/**
 * 144. 二叉树前序遍历
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class 前序 {

    /**
     * 递归解法
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static void preorderTraversal(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return;
        }

        result.add(treeNode.val);
        preorderTraversal(treeNode.left, result);
        preorderTraversal(treeNode.right, result);
    }

    /**
     * 迭代解法（借助栈）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> preorderTraversal(TreeNode treeNode) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 先入根节点
        stack.push(treeNode);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 根
            result.add(node.val);

            // 右
            if (node.right != null) {
                stack.push(node.right);
            }
            // 左
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * 迭代解法（借助栈）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> preorderTraversal2(TreeNode treeNode) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 辅助节点（作为当前遍历到的节点）
        TreeNode node = treeNode;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                // 读取前序遍历根节点
                result.add(node.val);
                // 压入栈，以便后续出栈访问右节点
                stack.push(node);
                // 继续遍历左边
                node = node.left;
            }

            // 弹出原先在栈顶的根节点，继续遍历他的右节点
            node = stack.pop();
            node = node.right;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<Integer>();
        preorderTraversal(TreeNode.demo(), result);
        System.out.println(result);

        System.out.println(preorderTraversal(TreeNode.demo()));
        System.out.println(preorderTraversal2(TreeNode.demo()));
    }
}
