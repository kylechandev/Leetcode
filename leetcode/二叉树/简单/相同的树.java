/*
 * @Author: kaic
 * @Date: 2022-11-26 13:36:32
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-23 13:50:30
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 100. 相同的树
 * 
 * 简单
 * 
 * 
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 
 * https://leetcode.cn/problems/same-tree/
 */
public class 相同的树 {

    /**
     * 迭代法 - 广度优先BFS
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode nodeP = queue.poll();
            TreeNode nodeQ = queue.poll();

            if (nodeP == null && nodeQ == null) {
                // 没有节点数据，继续匹配
                continue;
            }

            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                // 左右子树不匹配
                return false;
            }

            // 左子树比较
            queue.offer(nodeP.left);
            queue.offer(nodeQ.left);
            // 右子树比较
            queue.offer(nodeP.right);
            queue.offer(nodeQ.right);
        }

        return true;
    }

    /**
     * 递归法 - 深度优先DFS
     */
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        // 递归结束条件
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        if (p == null && q == null) {
            return true;
        }

        // 左右子树比较
        return p.val == q.val && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }

    public static void main(String[] args) {
        System.out.println(isSameTree2(TreeNode.demo(), TreeNode.demo()));
    }
}
