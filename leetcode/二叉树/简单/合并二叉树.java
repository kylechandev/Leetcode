/*
 * @Author: kaic
 * @Date: 2022-11-28 17:19:58
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-20 22:54:51
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 617. 合并二叉树 - 「深度优先搜索DFS」
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
     * 迭代法（模拟递归） - 「深度优先搜索DFS」
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
        // 此时初始的两个节点肯定都是不为空的
        stack.push(root2);
        stack.push(root1);

        while (!stack.isEmpty()) {
            // 出栈两个树的同位置的子节点
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            // 合并
            int sum = node1.val + node2.val;
            node1.val = sum;

            // 压入新的右子节点 - 先压右，因为栈是先进后出，我们需要先从左子节点遍历
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

            // 压入新的左子节点 - 后压左，因为栈是先进后出，我们需要先从左子节点遍历
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

    /**
     * 迭代法 - 「广度优先搜索BFS」
     */
    public TreeNode mergeTrees3(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // 表示合并后的二叉树节点
        Queue<TreeNode> queue = new LinkedList<>();
        // root1的二叉树节点
        Queue<TreeNode> queue1 = new LinkedList<>();
        // root2的二叉树节点
        Queue<TreeNode> queue2 = new LinkedList<>();

        TreeNode merged = new TreeNode(root1.val + root2.val);

        // 初始化
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);

        // 开始遍历，广度（横向）
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            // 取左右节点
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;

            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    // 合并值π
                    int sum = left1.val + left2.val;
                    TreeNode left = new TreeNode(sum);
                    node.left = left;

                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    // 取左节点
                    node.left = left1;
                } else if (left2 != null) {
                    // 取右节点
                    node.left = left2;
                }
            }

            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    int sum = right1.val + right2.val;
                    TreeNode right = new TreeNode(sum);
                    node.right = right;

                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else if (right2 != null) {
                    node.right = right2;
                }
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        TreeNode.levelPrint(
                new 合并二叉树().mergeTrees(
                        TreeNode.demo(new Integer[] { 1, 3, 2, 5 }),
                        TreeNode.demo(new Integer[] { 2, 1, 3, null, 4, null, 7 })));
    }
}
