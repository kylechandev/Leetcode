/*
 * @Author: kaic
 * @Date: 2022-11-14 15:18:15
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 20:00:24
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树;

import leetcode.二叉树.遍历.层序;

/**
 * 二叉树节点
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 通过数组创建二叉树
     * 
     * @param array       数组
     * @param parentIndex 父节点位置
     */
    private static TreeNode createBinaryTreeByArray(Integer[] array, int parentIndex) {
        TreeNode treeNode = null;

        if (parentIndex < array.length) {
            Integer value = array[parentIndex];
            if (value != null) {
                // 节点存在
                // 创建父节点
                treeNode = new TreeNode(value);

                // 左孩子
                treeNode.left = createBinaryTreeByArray(array, parentIndex * 2 + 1);
                // 右孩子
                treeNode.right = createBinaryTreeByArray(array, parentIndex * 2 + 2);
            }
        }

        // 返回根节点
        return treeNode;
    }

    /**
     * 一个普通二叉树
     */
    public static TreeNode demo() {
        // 创建二叉树的源数组
        // 先序 [1, 2, 4, 5, 3, 6]
        // 中序 [1, 2, 4, 5, 3, 6]
        // 后序 [1, 2, 4, 5, 3, 6]
        return demo(new Integer[] { 1, 2, 3, 4, 5, null, 6 });
    }

    /**
     * 自定义一个二叉树
     */
    public static TreeNode demo(Integer[] array) {
        return createBinaryTreeByArray(array, 0);
    }

    /**
     * 搜索某一个节点（假设二叉树的每一个节点的值都不相同）
     * 
     * @param value 需要搜索的值
     */
    public TreeNode searchNode(int value) {
        return searchTraverval(this, value);
    }

    /**
     * 递归遍历搜索二叉树的某一个节点
     */
    private TreeNode searchTraverval(TreeNode root, int value) {
        // 结束条件
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            return root;
        }

        // 递归找左子树
        TreeNode findLeft = searchTraverval(root.left, value);
        if (findLeft != null) {
            return findLeft;
        } else {
            // 继续递归找右子树
            TreeNode findRight = searchTraverval(root.right, value);
            return findRight;
        }
    }

    /**
     * 一个对称二叉树
     */
    public static TreeNode symmetric() {
        // https://leetcode.cn/problems/symmetric-tree/
        Integer[] array = new Integer[] { 1, 2, 2, 3, 4, 4, 3 };
        return createBinaryTreeByArray(array, 0);
    }

    public static class Tree {
        // 二叉树
        public TreeNode root;
        // 它的子树
        public TreeNode subRoot;

        public Tree(TreeNode root, TreeNode subRoot) {
            this.root = root;
            this.subRoot = subRoot;
        }
    }

    /**
     * 一个二叉树以及他的子树
     */
    public static Tree rootAndSubroot() {
        // https://leetcode.cn/problems/subtree-of-another-tree/
        Integer[] root = new Integer[] { 3, 4, 5, 1, 2 };
        Integer[] subRoot = new Integer[] { 4, 1, 2 };

        TreeNode rootNode = createBinaryTreeByArray(root, 0);
        TreeNode subRootNode = createBinaryTreeByArray(subRoot, 0);
        return new Tree(rootNode, subRootNode);
    }

    /**
     * 一个完全二叉树
     */
    public static TreeNode completeBinaryTree() {
        // https://leetcode.cn/problems/count-complete-tree-nodes/
        Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6 };
        return createBinaryTreeByArray(array, 0);
    }

    /**
     * 一个平衡二叉树
     */
    public static TreeNode balancedBinaryTree() {
        // https://leetcode.cn/problems/balanced-binary-tree/
        Integer[] array = new Integer[] { 3, 9, 20, null, null, 15, 7 };
        return createBinaryTreeByArray(array, 0);
    }

    /**
     * 一个非平衡二叉树
     */
    public static TreeNode balancedNotBinaryTree() {
        // https://leetcode.cn/problems/balanced-binary-tree/
        Integer[] array = new Integer[] { 1, 2, 2, 3, 3, null, null, 4, 4 };
        return createBinaryTreeByArray(array, 0);
    }

    /**
     * 层序打印二叉树
     */
    public static void levelPrint(TreeNode root) {
        System.out.println(层序.levelOrder(root));
    }
}
