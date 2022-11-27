/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-27 10:23:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 临时算法
 */
public class Test {

    public static TreeNode generateTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        q.offer(root);
        int k = 1;

        while (k < nums.length) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = q.poll();
                if (nums[k] != null) {
                    TreeNode node = new TreeNode(nums[k]);
                    p.left = node;
                    q.offer(node);
                } else {
                    p.left = null;
                }
                k++;
                if (nums[k] != null) {
                    TreeNode node = new TreeNode(nums[k]);
                    p.right = node;
                    q.offer(node);
                } else {
                    p.right = null;
                }
                k++;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        generateTree(new Integer[] { 1, 2, 3, 4, null, 5, 6, null, null, 7 });
    }
}
