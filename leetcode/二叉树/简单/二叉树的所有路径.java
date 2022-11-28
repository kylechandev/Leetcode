package leetcode.二叉树.简单;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import leetcode.二叉树.TreeNode;

/**
 * 257. 二叉树的所有路径
 * 
 * 简单
 * 
 * 
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 
 * https://leetcode.cn/problems/binary-tree-paths/
 */
public class 二叉树的所有路径 {

    public static List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> result = new ArrayList<>();

        transval(root, path, result);
        return result;
    }

    /**
     * 递归解法（先序-根左右遍历）
     * 
     * @param root   当前节点（确保不为null）
     * @param path   一条路径
     * @param result 所有路径结果集合
     */
    private static void transval(TreeNode root, List<Integer> path, List<String> result) {
        if (root == null) {
            return;
        }

        // 根
        // 写在终止条件前，让最后一个叶子节点能够添加进来
        path.add(root.val);

        // 终止条件：
        // 到达叶子节点时
        if (root.left == null && root.right == null) {
            // 遍历到叶子节点时，表示一条路径已经遍历完成了，此时将`路径`添加到结果集合中
            result.add(pathConvert(path));

            // return;
        }

        // if (root.left != null) {
            // 继续遍历左节点
            transval(root.left, path, result);
            // 回溯
            // path.remove(path.size() - 1);
        // }

        // if (root.right != null) {
            // 继续遍历右节点
            transval(root.right, path, result);
            // 回溯
            path.remove(path.size() - 1);
        // }
    }

    private static String pathConvert(List<Integer> path) {
        return path.stream().map(String::valueOf).collect(Collectors.joining("->"));
    }

    /**
     * 迭代法
     */
    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Object> stack = new LinkedList<>();

        // 同时入节点和值
        stack.push(root);
        stack.push(root.val + "");

        while (!stack.isEmpty()) {
            // 同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();

            if (node.left == null && node.right == null) {
                // 到叶子节点了
                result.add(path);
            } else {
                if (node.right != null) {
                    // 继续遍历右节点
                    stack.push(node.right);
                    stack.push(path + "->" + node.right.val);
                }
                if (node.left != null) {
                    // 继续遍历左节点
                    stack.push(node.left);
                    stack.push(path + "->" + node.left.val);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(binaryTreePaths(TreeNode.demo(new Integer[] { 1, 2, 3, null, 5 })));
    }
}
