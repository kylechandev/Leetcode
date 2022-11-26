package leetcode.二叉树.遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.二叉树.TreeNode;

/**
 * 94. 二叉树的中序遍历
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class 中序 {

    /**
     * 递归解法
     */
    public static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    /**
     * 迭代解法
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = root;

        while (!stack.isEmpty() || treeNode != null) {
            // 先遍历到最深的左节点
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            // 遍历左节点
            treeNode = stack.pop();
            result.add(treeNode.val);
            // 遍历右节点
            treeNode = treeNode.right;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversal(TreeNode.demo(), result);
        System.out.println(result);

        System.out.println(inorderTraversal(TreeNode.demo()));
    }
}
