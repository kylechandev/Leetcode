/*
 * @Author: kaic
 * @Date: 2022-11-28 17:19:58
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-28 22:51:09
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.Deque;
import java.util.LinkedList;

import leetcode.二叉树.TreeNode;

/**
 * 617. 合并二叉树
 * 
 * 简单
 * 
 * 
 * 给你两棵二叉树： root1 和 root2 。
 * 
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
 * 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；
 * 否则，不为null 的节点将直接作为新二叉树的节点。
 * 
 * 返回合并后的二叉树。
 * 
 * 注意: 合并过程必须从两个树的根节点开始。
 * 
 * 
 * https://leetcode.cn/problems/merge-two-binary-trees/
 */
public class 合并二叉树 {

    /**
     * 递归法
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 终止条件
        if (root1 == null) {
            // root1节点为空时，那么合并之后就应该是root2（此时不需要关心root2是否为空，是不是空都无所谓）
            return root2;
        }
        if (root2 == null) {
            // root2节点为空时，那么合并之后就应该是root1
            return root1;
        }

        // 合并两个存在的节点（重复利用root1这个树）
        int sum = root1.val + root2.val;
        root1.val = sum;
        // root1的最终左子树为`合并了root1的左子树和root2的左子树的结果`
        root1.left = mergeTrees(root1.left, root2.left);
        // root1的最终右子树为`合并了root1的右子树和root2的右子树的结果`
        root1.right = mergeTrees(root1.right, root2.right);

        // 返回重复利用的root1这棵树
        return root1;
    }

    /**
     * 迭代法
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            // root1节点为空时，那么合并之后就应该是root2（此时不需要关心root2是否为空，是不是空都无所谓）
            return root2;
        }
        if (root2 == null) {
            // root2节点为空时，那么合并之后就应该是root1
            return root1;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root2);
        stack.push(root1);

        while (!stack.isEmpty()) {
            // 出栈两个树的同位置的子节点
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            // 合并
            int sum = node1.val + node2.val;
            node1.val = sum;

            // 压入新的右子节点
            if (node2.right != null && node1.right != null) {
                // 压入两个树的同一个右子节点，准备接下来出栈合并
                stack.push(node2.right);
                stack.push(node1.right);
            } else {
                // 有一个树的此节点为空，合并之后为不为空的那个节点
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }

            // 压入新的左子节点
            if (node2.left != null && node1.left != null) {
                // 压入两个树的同一个左子节点，准备接下来出栈合并
                stack.push(node2.left);
                stack.push(node1.left);
            } else {
                // 有一个树的此节点为空，合并之后为不为空的那个节点
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
        }

        // 重复利用root1树，直接返回root1
        return root1;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(
                new 合并二叉树().mergeTrees(
                        TreeNode.demo(new Integer[] { 1, 3, 2, 5 }),
                        TreeNode.demo(new Integer[] { 2, 1, 3, null, 4, null, 7 })));
    }
}
