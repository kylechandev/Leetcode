package leetcode.二叉树.中等;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 113. 路径总和 II
 * 
 * 中等
 * 
 * 
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 
 * https://leetcode.cn/problems/path-sum-ii/
 */
public class 路径总和2 {

    static List<List<Integer>> result = new LinkedList<List<Integer>>();
    static Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    /**
     * 迭代 - 广度优先搜索
     * 
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result.clear();
        map.clear();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(root.val);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            Integer sum = queueSum.poll();

            if (node.left == null && node.right == null) {
                // 遍历到叶子节点了
                if (sum == targetSum) {
                    // 符合要求的一条路径，提取路径
                    getPath(node);
                }
            } else {
                // 继续遍历左右节点
                if (node.left != null) {
                    map.put(node.left, node); // 记住路径

                    queueNode.offer(node.left);
                    queueSum.offer(sum + node.left.val);
                }
                if (node.right != null) {
                    map.put(node.right, node); // 记住路径

                    queueNode.offer(node.right);
                    queueSum.offer(sum + node.right.val);
                }
            }
        }

        return result;
    }

    /**
     * 提取出路径
     * 
     * @param node 叶子节点
     */
    private static void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();
        while (node != null) {
            // 不断的添加它的父节点，还原路径
            temp.add(0, node.val);
            node = map.get(node);
        }

        result.add(temp);
    }

    /**
     * 递归 - 深度优先搜索
     * 
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public static List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                pathSum(TreeNode.demo(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 }), 22));
    }
}
