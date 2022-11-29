package leetcode.二叉树.简单;

import leetcode.二叉树.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 
 * 简单
 * 
 * 
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 
 * 
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 */
public class 二叉搜索树的最小绝对差 {

    /**
     * 递归法
     */
    public int getMinimumDifference(TreeNode root) {
        traversal(root);
        return min;
    }

    private TreeNode preNode = null;
    private Integer min = null;

    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }

        traversal(root.left);
        if (preNode != null) {
            int diff = Math.abs(preNode.val - root.val);
            if (min == null) {
                min = diff;
            } else {
                min = Math.min(min, diff);
            }
        }
        preNode = root;
        traversal(root.right);
    }

    public static void main(String[] args) {
        System.out.println(
                new 二叉搜索树的最小绝对差().getMinimumDifference(
                        TreeNode.demo(new Integer[] { 4, 2, 6, 1, 3 })));
    }
}
