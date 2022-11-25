/*
 * @Author: kaic
 * @Date: 2022-11-14 21:13:14
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-15 16:47:37
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.遍历;

import java.util.ArrayList;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 二叉树的后序遍历
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

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<Integer>();
        postorderTraversal(TreeNode.demo(), result);
        System.out.println(result);

        postorderTraversal(TreeNode.demo());
    }
}
