package leetcode.二叉树.遍历;

import java.util.Collections;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 107. 二叉树的层序遍历 II
 * 
 * 中等
 * 
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 */
public class 层序2 {

    /**
     * 直接使用层序遍历的结果，再反转即可实现
     */
    public static void main(String[] args) {
        List<List<Integer>> result = 层序.levelOrder(TreeNode.demo());
        Collections.reverse(result);

        System.out.println(result);
    }
}
