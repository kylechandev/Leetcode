package leetcode.二叉树.简单;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiConsumer;

import leetcode.二叉树.TreeNode;

/**
 * 637. 二叉树的层平均值
 * 
 * 简单
 * 
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * 
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 */
public class 二叉树的层平均值 {

    /**
     * 太low了...用到了太多数据结构
     * 
     * 广度优先搜索1（效果最差）
     * 
     * 时间10 ms 击败2.38%
     * 内存44.3 MB 击败5.2%
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();

        // <depth, sum_value>
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Double> mapSum = new HashMap<>();

        // node queue
        Queue<TreeNode> queueNode = new ArrayDeque<>();
        queueNode.offer(root);

        // depth queue
        Queue<Integer> queueDepth = new ArrayDeque<>();
        queueDepth.offer(0); // 初始第0层

        while (!queueNode.isEmpty()) {
            // 取出当前节点 和 当前节点所在的深度
            TreeNode node = queueNode.poll();
            Integer depth = queueDepth.poll();

            if (node != null) {
                // 保存每层的数据
                if (map.containsKey(depth)) {
                    map.put(depth, map.get(depth) + node.val);
                    mapSum.put(depth, mapSum.get(depth) + 1.0);
                } else {
                    map.put(depth, Double.valueOf(node.val));
                    mapSum.put(depth, 1.0);
                }

                // 继续遍历节点
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

        map.forEach(new BiConsumer<Integer, Double>() {
            @Override
            public void accept(Integer depth, Double sum) {
                Double count = mapSum.get(depth);
                if (count != 0) {
                    double average = sum / count;
                    result.add(average);
                }
            }
        });

        return result;
    }

    /**
     * 广度优先搜索（效果一般）
     * 
     * 时间3 ms 击败24.81%
     * 内存42.6 MB 击败81.75%
     */
    public static List<Double> averageOfLevels2(TreeNode root) {
        List<Double> result = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            double size = queue.size();

            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += Double.valueOf(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(sum / size);
        }

        return result;
    }

    /**
     * 深度优先搜索（效果最好）
     * 
     * 时间1 ms 击败100%
     * 内存42.9 MB 击败49.6%
     */
    public static List<Double> averageOfLevels3(TreeNode root) {
        // 存放每一层的节点值总和
        List<Double> sums = new ArrayList<>();
        // 存放每一层的节点数
        List<Double> counts = new ArrayList<>();

        dfs(root, 0, sums, counts);

        List<Double> result = new ArrayList<>();
        for (int i = 0; i < sums.size(); i++) {
            result.add(sums.get(i) / counts.get(i));
        }

        return result;
    }

    private static void dfs(TreeNode treeNode, int depth, List<Double> sums, List<Double> counts) {
        if (treeNode == null) {
            return;
        }

        if (depth < sums.size()) {
            // 回溯到前几层的节点，开始累加
            sums.set(depth, sums.get(depth) + Double.valueOf(treeNode.val));
            counts.set(depth, counts.get(depth) + 1);
        } else {
            // 首次访问某一层
            sums.add(Double.valueOf(treeNode.val));
            counts.add(Double.valueOf(1));
        }

        dfs(treeNode.left, depth + 1, sums, counts);
        dfs(treeNode.right, depth + 1, sums, counts);
    }

    public static void main(String[] args) {
        System.out.println(averageOfLevels3(TreeNode.demo()));
    }
}
