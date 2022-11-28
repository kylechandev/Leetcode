package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.二叉树.TreeNode;

/**
 * 700. 二叉搜索树中的搜索
 * 
 * 简单
 * 
 * 
 * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * 
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
 * 
 * 示例：
 * 输入：root = [4,2,7,1,3], val = 2
 * 输出：[2,1,3]
 * 
 * https://leetcode.cn/problems/search-in-a-binary-search-tree/
 */
public class 二叉搜索树中的搜索 {

    /**
     * 递归解法
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            // 找到了
            return root;
        } else if (val < root.val) {
            // 往左子树找
            return searchBST(root.left, val);
        } else {
            // 往右子树找
            return searchBST(root.right, val);
        }
    }

    /**
     * 迭代解法
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (val == node.val) {
                // 找到了
                return node;
            } else if (val < node.val) {
                // 左
                stack.push(node.left);
            } else {
                // 右
                stack.push(node.right);
            }
        }

        // 没找到
        return null;
    }

    public static void main(String[] args) {
        // TreeNode.levelPrint(TreeNode.demo(new Integer[] { 4, 2, 7, 1, 3 }).left);
        TreeNode.levelPrint(new 二叉搜索树中的搜索().searchBST2(TreeNode.demo(new Integer[] { 4, 2, 7, 1, 3 }), 2));
    }
}
