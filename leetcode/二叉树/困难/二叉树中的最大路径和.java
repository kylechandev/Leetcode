/*
 * @Author: kaic
 * @Date: 2023-05-06 10:59:04
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-06 11:23:02
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.困难;

import leetcode.二叉树.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 
 * 困难
 * 
 * 
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/solutions/
 * 
 * 【题解】
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/solutions/18040/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-ikaruga/
 */
public class 二叉树中的最大路径和 {

    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 整体属于「后序遍历」

        // 左右子树的贡献最大路径和
        int maxLeft = dfs(root.left);
        int maxRight = dfs(root.right);

        // 当前子树内部的最大路径和
        int max = root.val + maxLeft + maxRight;
        ans = Math.max(ans, max);

        // 当前子树能够对外(父亲)贡献的最大路径和（因为是子树，那肯定要带上子树的root，不然构成不了一颗树啊）
        int pass = Math.max(maxLeft, maxRight) + root.val;

        // 如果当前节点向上（父亲）贡献的值是负数，那么直接告诉上面的节点不要往我这里走
        // 这个判断负数的逻辑，判断的内部一个子树的路劲和是负数，而不是某个节点的值是负数！！！
        // 因为我们是从底向上传递值的（所以说是向上累加），底部一个子树的路径已经是负数了，那本身就没有必要再去考虑了
        return pass < 0 ? 0 : pass;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.demo(new Integer[] { -10, 9, 20, null, null, 15, 7 });

        二叉树中的最大路径和 demo = new 二叉树中的最大路径和();
        System.out.println(demo.maxPathSum(root));
    }
}
