package leetcode.二叉树.中等;

import java.util.ArrayList;

import leetcode.二叉树.TreeNode;

/**
 * 99. 恢复二叉搜索树
 * 
 * 中等
 * 
 * 
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 * 
 * 
 * https://leetcode.cn/problems/recover-binary-search-tree/
 */
public class 恢复二叉搜索树 {

    /**
     * 额外借助Array保存二叉树的遍历顺序
     */
    public void recoverTree(TreeNode root) {
        ArrayList<TreeNode> result = new ArrayList<>();

        // 因为是二叉搜索树，所以中序遍历的结果顺序的
        // 获得 中序遍历 的节点顺序
        dfs1(root, result);

        // 找到数组中逆序的位置
        TreeNode x = null;
        TreeNode y = null;

        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).val > result.get(i + 1).val) {
                // 获取最后一个逆序数的位置
                y = result.get(i + 1);

                if (x == null) {
                    // 获取第一个逆序数的位置
                    x = result.get(i);
                }
            }
        }

        // 找到了两个逆序数所在的位置
        if (x != null && y != null) {
            // 交换两个节点的值，不用交换实际节点
            int temp = x.val;
            x.val = y.val;
            y.val = temp;
        }
    }

    private void dfs1(TreeNode root, ArrayList<TreeNode> result) {
        if (root == null) {
            return;
        }

        dfs1(root.left, result);
        result.add(root);
        dfs1(root.right, result);
    }

    // 用两个变量x，y来记录需要交换的节点
    private TreeNode xx = null;
    private TreeNode yy = null;
    private TreeNode pre = null;

    /**
     * 通过 pre指针 记录上一次遍历的节点
     */
    public void recoverTree2(TreeNode root) {
        dfs2(root);

        // 找到了两个逆序数所在的位置
        if (xx != null && yy != null) {
            // 交换两个节点的值，不用交换实际节点
            int temp = xx.val;
            xx.val = yy.val;
            yy.val = temp;
        }
    }

    private void dfs2(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs2(root.left);

        if (pre == null) {
            pre = root;
        } else {
            if (pre.val > root.val) {
                yy = root;
                if (xx == null) {
                    xx = pre;
                }
            }
            pre = root;
        }

        dfs2(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.demo(new Integer[] { 1, 3, null, null, 2 });

        恢复二叉搜索树 demo = new 恢复二叉搜索树();
        demo.recoverTree(root);
    }
}
