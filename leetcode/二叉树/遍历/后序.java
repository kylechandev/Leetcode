/*
 * @Author: kaic
 * @Date: 2022-11-14 21:13:14
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-25 09:54:35
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.遍历;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 145. 二叉树的后序遍历
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class 后序 {

    /**
     * 递归解法
     */
    public static void postorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }

    /**
     * 迭代解法
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 入栈顺序：根-左-右（先序） 出栈顺序：根-右-左， 最后翻转结果

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);

            TreeNode left = treeNode.left;
            if (left != null) {
                stack.push(left);
            }
            TreeNode right = treeNode.right;
            if (right != null) {
                stack.push(right);
            }
        }

        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<Integer>();
        postorderTraversal(TreeNode.demo(), result);
        System.out.println(result);

        System.out.println(postorderTraversal(TreeNode.demo()));
    }
}
