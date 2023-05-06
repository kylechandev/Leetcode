/*
 * @Author: kaic
 * @Date: 2023-05-06 13:05:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-06 13:23:24
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.中等;

import java.util.ArrayList;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 129. 求根节点到叶节点数字之和
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/sum-root-to-leaf-numbers/
 */
public class 求根节点到叶节点数字之和 {

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private int list2Int(List<Integer> list) {
        int s = 0;
        for (int i = 0; i < list.size(); i++) {
            s = s * 10 + list.get(i);
        }

        return s;
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        path.add(root.val);
        dfs(root);

        System.out.println("结果：" + ans);
        int sum = 0;
        for (List<Integer> list : ans) {
            sum += list2Int(list);
        }

        return sum;
    }

    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            // 已经遍历到叶子节点，得到一条路径
            ans.add(new ArrayList<>(path));
            return;
        }

        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left);
            path.remove(path.size() - 1); // 递归回溯一一对应
        }
        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right);
            path.remove(path.size() - 1); // 递归回溯一一对应
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.demo(new Integer[] { 1, 2, 3 });

        求根节点到叶节点数字之和 demo = new 求根节点到叶节点数字之和();

        System.out.println(demo.sumNumbers(root));
    }
}
