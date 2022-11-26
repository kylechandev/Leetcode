package leetcode.二叉树.简单;

import leetcode.二叉树.TreeNode;

/**
 * 572. 另一棵树的子树
 * 
 * 简单
 * 
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
 * 如果存在，返回 true；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * 
 * 
 * 此题还可用KMP匹配算法求解
 * https://leetcode.cn/problems/subtree-of-another-tree/solutions/233896/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/#:~:text=%E3%80%82-,%E6%96%B9%E6%B3%95%E4%BA%8C%EF%BC%9A%E6%B7%B1%E5%BA%A6%E4%BC%98%E5%85%88%E6%90%9C%E7%B4%A2%E5%BA%8F%E5%88%97%E4%B8%8A%E5%81%9A%E4%B8%B2%E5%8C%B9%E9%85%8D,-%E6%80%9D%E8%B7%AF%E5%92%8C%E7%AE%97%E6%B3%95
 * 
 * 
 * https://leetcode.cn/problems/subtree-of-another-tree/
 */
public class 另一棵树的子树 {

    /**
     * 深度优先搜索暴力匹配
     * 
     * 深度搜索以root树的每一个子节点作为root节点的树是否与subRoot树相等
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (相同的树.isSameTree2(root, subRoot)) {
            // 相同的树
            return true;
        } else if (root == null && subRoot == null) {
            // 两个都为空树，返回true
            return true;
        } else if (root == null) {
            // 只有root树为空，返回false
            return false;
        } else {
            // 继续比较以root.left为root的子树是否与subRoot相等
            // 继续比较以root.right为root的子树是否与subRoot相等
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    public static void main(String[] args) {
        TreeNode.Tree tree = TreeNode.rootAndSubroot();
        System.out.println(isSubtree(tree.root, tree.subRoot));
    }
}
