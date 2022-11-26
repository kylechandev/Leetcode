package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 */
public class 完全二叉树的节点个数 {

    /**
     * 普通二叉树
     * 
     * 可以直接计算
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 先求它的左子树的节点数量
        // 再求它的右子树的节点数量
        // 最后取总和再加一 （加1是因为算上当前中间节点）就是目前节点为根节点的节点数量
        return countNodes(root.left) + countNodes(root.right) + 1; // 左 右 根 的遍历顺序
    }

    /**
     * 利用满二叉树的特性
     * 
     * 节点数量 = 2^depth - 1
     */
    public static int countNodes2(TreeNode root) {
        int depth = 0;

        TreeNode treeNode = root;
        while (treeNode != null) {
            // 不断遍历一侧节点，计算二叉树的深度
            depth++;
            treeNode = treeNode.left;
        }

        return (1 << depth) - 1; // `<<`优先级低于`-`
    }

    /**
     * 利用完全二叉树的特性（计算方式可以看作普通二叉树和满二叉树的结合）
     * 
     * “完全二叉树的左右子树，总有一侧是满二叉树（单独一个子节点也算是满二叉树）”
     * 
     * 【注意】
     *  ！！这个方法要求传入的root必须是符合完全二叉树的，否则计算结果会出错！！例如{ 1, 2, 3, 4, 5, null, 6 }就不符合要求，它不是一个完全二叉树
     * 
     * 
     * 时间复杂度：O(logN*logN)
     */
    public static int countNodes3(TreeNode root) {
        // 计算左子树的深度
        int depthLeft = 0;
        TreeNode leftNode = root;
        while (leftNode != null) {
            depthLeft++;
            leftNode = leftNode.left;
        }

        // 计算右子树的深度
        int depthRight = 0;
        TreeNode rightNode = root;
        while (rightNode != null) {
            depthRight++;
            rightNode = rightNode.right;
        }

        if (depthLeft == depthRight) {
            // 左右子树深度相同，说明是一颗满二叉树
            return (1 << depthLeft) - 1;
        } else {
            // 否则按照普通二叉树来计算
            // 这两个递归只有一个会真的递归下去，另一个一定会触发 depthLeft == depthRight 而立即返回，不会递归下去
            // 由于完全二叉树的性质，其子树一定有一棵是满的，所以一定会触发 hl == hr，只消耗 O(logN) 的复杂度而不会继续递归
            // https://labuladong.github.io/algo/2/21/48/
            return countNodes3(root.left) + countNodes3(root.right) + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(countNodes3(TreeNode.completeBinaryTree()));
    }
}
