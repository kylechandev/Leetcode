package leetcode.二叉树.中等;

import leetcode.二叉树.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 
 * 中等
 * 
 * 
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node
 * 的新值等于原树中大于或等于 node.val 的值之和。
 * 
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038:
 * https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 * 
 * 
 * 【理解：相当于有序数组从后往前累加值】
 * [2,5,13] -> [20,18,13]
 * 
 * 
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 */
public class 把二叉搜索树转换为累加树 {

    private TreeNode preNode = null;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 右
        convertBST(root.right);

        // 中
        if (preNode == null) {
            preNode = root;
        } else {
            // 开始累加
            int sum = root.val + preNode.val;
            root.val = sum;

            preNode = root;
        }

        // 左
        convertBST(root.left);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.demo(new Integer[] { 4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8 });
        TreeNode.levelPrint(new 把二叉搜索树转换为累加树().convertBST(node));
    }
}
