package leetcode.二叉树.中等;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import leetcode.二叉树.TreeNode;

/**
 * 199. 二叉树的右视图
 * 
 * 中等
 * 
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 【注意】
 * 不是单纯的返回`右侧`的最后一个节点，而是返回`每层`的最后一个节点（如果一层中只有左子树没有右子树，那么需要返回左子树的最后一个节点）
 * 
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class 二叉树的右视图 {

    /**
     * 方法一
     * 
     * 借助层序遍历，并返回每一层的最后一个节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }

                if (i == size - 1) {
                    // 把最后一个节点加入
                    result.add(treeNode.val);
                }
            }
        }

        return result;
    }

    /**
     * 方法二 - 官方题解
     * 
     * 深度优先搜索
     * 
     * https://leetcode.cn/problems/binary-tree-right-side-view/solutions/213494/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     */
    public static List<Integer> rightSideViewDepth(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // 1、总是从右侧节点开始遍历搜索
        // 2、记录每一层的首次遍历到的右侧节点

        // 记录节点遍历（深度，所以使用栈，因为遍历到最底下后要再回溯回去）
        Deque<TreeNode> stackNode = new ArrayDeque<>();
        stackNode.push(root);

        // 记录节点所在的层数（深度，所以使用栈，因为遍历到最底下后要再回溯回去）
        Deque<Integer> stackDepth = new ArrayDeque<>();
        stackDepth.push(0);

        // 需要记录每一层的右侧节点 <depth, value>
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        while (!stackNode.isEmpty()) {
            // 当前节点
            TreeNode treeNode = stackNode.pop();
            // 当前节点所在的深度
            int depth = stackDepth.pop();

            if (treeNode != null) {
                if (!map.containsKey(depth)) {
                    map.put(depth, treeNode.val);
                }

                // 继续遍历
                if (treeNode.left != null) {
                    stackNode.push(treeNode.left);
                    stackDepth.push(depth + 1);
                }
                if (treeNode.right != null) {
                    stackNode.push(treeNode.right);
                    stackDepth.push(depth + 1);
                }
            }
        }

        result.addAll(map.values());

        return result;
    }

    /**
     * 方法二
     * 
     * 深度优先搜索（递归）
     */
    public static List<Integer> rightSideViewDepth2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);

        return result;
    }

    /**
     * @param treeNode 遍历的当前节点
     * @param depth    当前节点的深度
     * @param result   结果集合，存放每一层的最右侧节点
     */
    private static void dfs(TreeNode treeNode, int depth, List<Integer> result) {
        if (treeNode == null) {
            return;
        }

        // result.size()就等同于depth了，因为里面只存放每一层的最右侧节点，并且depth也是从0开始，
        // 所以当depth==result.size()时，就表示是第depth层的右侧节点第一次访问，也就是第depth的最右侧节点
        if (depth == result.size()) {
            result.add(treeNode.val);
        }

        // 先访问右节点
        dfs(treeNode.right, depth + 1, result);
        // 再访问左节点
        dfs(treeNode.left, depth + 1, result);
    }

    /**
     * 方法三 - 官方题解
     * 
     * 广度优先搜索
     * 
     * https://leetcode.cn/problems/binary-tree-right-side-view/solutions/213494/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     */
    public static List<Integer> rightSideViewWidely(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        // 记录遍历的节点（广度了，也就是层序遍历，所以这个改用队列了）
        Queue<TreeNode> queueNode = new ArrayDeque<>();
        queueNode.offer(root);

        // 记录遍历的节点所在的深度（广度了，也就是层序遍历，所以这个改用队列了）
        Queue<Integer> queueDepth = new ArrayDeque<>();
        queueDepth.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int depth = queueDepth.poll();

            if (node != null) {
                // 保存这一层的数据， 因为是广度优先遍历，相当于是层序，所以一直覆盖知道最后一个右节点
                map.put(depth, node.val);

                // 继续遍历左右节点
                if (node.left != null) {
                    queueNode.offer(node.left);
                    queueDepth.offer(depth + 1);
                }
                if (node.right != null) {
                    queueNode.offer(node.right);
                    queueDepth.offer(depth + 1);
                }
            }
        }

        result.addAll(map.values());

        return result;
    }

    public static void main(String[] args) {
        System.out.println(rightSideViewWidely(TreeNode.demo()));
    }
}
