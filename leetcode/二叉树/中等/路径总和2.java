package leetcode.二叉树.中等;

import java.util.Deque;
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

    private final List<List<Integer>> result = new LinkedList<List<Integer>>();
    private final Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    /**
     * 迭代 - 广度优先搜索
     * 
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
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
    private void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();
        while (node != null) {
            // 不断的添加它的父节点，还原路径
            temp.add(0, node.val);
            node = map.get(node);
        }

        result.add(temp);
    }

    /**
     * 递归 - 深度优先搜索DFS
     * 
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        dfs(root, targetSum);

        return result;
    }

    /**
     * Deque表示栈时不要使用 push pop
     * push 用 offerLast
     * pop 用 pollLast
     * 
     * 因为Deque(双端队列)的 push pop 都是从队头添加/删除，要么你就使用Stack，不用Deque
     */
    private final Deque<Integer> path2 = new LinkedList<>();

    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }

        path2.offerLast(root.val);

        int newTarget = targetSum - root.val;

        if (root.left == null && root.right == null && newTarget == 0) {
            // 找到了一条路径
            result.add(new LinkedList<>(path2));
        }

        // 回溯一次
        // 是因为始终都会进入递归，然后通过最一开始的 root == null 判断跳出，
        // 也就是当已经遍历到叶子节点时，还是继续进入递归（虽然此时已经没有意义了），递归这个叶子节点的左右子节点，
        // 但因为叶子节点的左右子节点肯定都是null，所以必定会 return 掉，也就是递归最后还是出在那个 叶子节点 的位置，
        // 所以只用回溯一次，把 最后的那个叶子节点 回退即可
        // （可以画一棵树模拟走一条路就了然了）

        dfs(root.left, newTarget);
        dfs(root.right, newTarget);
        path2.pollLast();
    }

    // 第二种写法
    private void dfs2(TreeNode root, int targetSum) {
        path2.offerLast(root.val);

        int newTarget = targetSum - root.val;

        if (root.left == null && root.right == null && newTarget == 0) {
            // 找到了一条路径
            result.add(new LinkedList<>(path2));
        }

        // 回溯两次
        // 这种情况是当遇到 左右子节点 为空时，就不进入递归了，每次递归的过程中都是确实存在的节点，
        // 所以每次递归结束后，都需要进行一次回溯
        // （可以画一棵树模拟走一条路就了然了）

        if (root.left != null) {
            dfs(root.left, newTarget);
            path2.pollLast();
        }
        if (root.right != null) {
            dfs(root.right, newTarget);
            path2.pollLast();
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.demo(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 });
        int target = 22;

        路径总和2 demo = new 路径总和2();
        System.out.println(demo.pathSum2(treeNode, target));
    }
}
