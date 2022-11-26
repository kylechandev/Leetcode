/*
 * @Author: kaic
 * @Date: 2022-11-26 16:28:28
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 19:17:25
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import leetcode.二叉树.TreeNode;

/**
 * 110. 平衡二叉树
 * 
 * 简单
 * 
 * 
 * 给定一个二叉树，判断它是否是`高度平衡`的二叉树。
 * 
 * 本题中，一棵高度平衡二叉树定义为：
 * [一个二叉树每个节点 的左右两个子树的`高度差`的绝对值不超过 1 ]
 * 
 * 
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
public class 平衡二叉树 {

    public static boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 计算二叉树的高度
     * 
     * 自底向上的递归
     * 
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回−1。
     * 如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     * 
     * 
     * @param root 以某个节点为root节点
     * @return 二叉树的高度，如果返回-1表示不是平衡二叉树
     */
    private static int getHeight(TreeNode root) {
        // 递归结束条件
        if (root == null) {
            // 返回0，表示以当前节点为根节点的树的高度为0
            return 0;
        }

        // 分别计算左右子树的高度
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(isBalanced(TreeNode.balancedBinaryTree()));
    }
}
