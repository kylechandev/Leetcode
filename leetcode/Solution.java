/*
 * @Author: kaic
 * @Date: 2022-11-28 11:10:33
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-28 11:20:08
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import leetcode.二叉树.TreeNode;

class Solution {

    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }

        // 遍历到一个新的节点
        path.offerLast(root.val);
        targetSum -= root.val;

        if (root.left == null && root.right == null && targetSum == 0) {
            // 遍历到叶子节点
            ret.add(new LinkedList<Integer>(path));
        }

        // 分别遍历root节点的左右子树的路径
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);

        // 一条路径遍历完成，弹出栈顶的当前路径的叶子节点，
        // 并继续执行`dfs(root.right, targetSum);`（也就是回到当前节点的父节点，继续去遍历它父节点的右侧子节点所在的路径）
        path.pollLast();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(
                solution.pathSum(TreeNode.demo(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 }),
                        22));
    }
}
