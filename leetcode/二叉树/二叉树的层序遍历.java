package leetcode.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class 二叉树的层序遍历 {

    /**
     * 通过数组创建二叉树
     * 
     * @param array       数组
     * @param parentIndex 当前父节点位置「关键点，通过父节点计算节点位置」
     */
    public static TreeNode createBinaryTreeByArray(Integer[] array, int parentIndex) {
        TreeNode treeNode = null;

        if (parentIndex < array.length) {
            // 节点数据
            Integer data = array[parentIndex];

            if (data != null) {
                // 节点存在
                // 创建父节点
                treeNode = new TreeNode(data);

                // 左孩子
                treeNode.left = createBinaryTreeByArray(array, parentIndex * 2 + 1);
                // 右孩子
                treeNode.right = createBinaryTreeByArray(array, parentIndex * 2 + 2);
            }
        }

        // 返回根节点
        return treeNode;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 最终结果
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // 创建一个列表（存放一行的节点）
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        // 先入队列根节点
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 当前行的长度
            int lineLength = queue.size();

            // 表示每行
            List<Integer> line = new ArrayList<>();

            // 读取这一行的所有元素
            for (int i = 0; i < lineLength; i++) {
                TreeNode treeNode = queue.poll();
                line.add(treeNode.val);

                // 开始入队列的左右子节点
                TreeNode left = treeNode.left;
                if (left != null) {
                    queue.offer(left);
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    queue.offer(right);
                }
            }

            // 添加这一行的数据
            result.add(line);
        }

        return result;
    }

    public static void main(String[] args) {
        // 创建二叉树的源数组
        Integer[] array = new Integer[] { 1, 2, 3, 4, 5, null, 6 };

        // 创建二叉树
        TreeNode treeNode = createBinaryTreeByArray(array, 0);

        List<List<Integer>> result = levelOrder(treeNode);
        System.out.println(result);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
