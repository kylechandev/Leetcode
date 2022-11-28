package leetcode.二叉树.中等;

import java.util.Arrays;

import leetcode.二叉树.TreeNode;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 
 * 中等
 * 
 * 给定两个整数数组 inorder 和 postorder ，
 * 其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 
 * 
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * 
 * 
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class 从中序与后序遍历序列构造二叉树 {

    /**
     * 递归解法
     * 
     * @param inorder   中序遍历结果 - 左根右
     * @param postorder 后续遍历结果 - 左右根
     * @return 根节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 1、后序数组为空，说明没有节点，返回null
        if (postorder.length == 0) {
            return null;
        }
        // 2、后序数组的最后一个作为根节点
        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue); // 构建根节点
        // 3、如果后序数组size=1，说明`原始二叉树只有一个节点`或者说遍历到了`叶子节点`，那么直接返回
        if (postorder.length == 1) {
            return root;
        }
        // 4、根据从后序数组中找到的根节点，通过中序数组计算得到切割点
        int splitIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                splitIndex = i;
                break;
            }
        }
        // 5、切割中序数组
        int[] newLeftInorder = Arrays.copyOfRange(inorder, 0, splitIndex);
        int[] newRightInorder = Arrays.copyOfRange(inorder, splitIndex + 1, inorder.length);
        System.out.println("中序-左区间：" + Arrays.toString(newLeftInorder));
        System.out.println("中序-右区间：" + Arrays.toString(newRightInorder));
        // 6、切割后序数组
        // 通过中序已经切割出左右区间，所以后序数组切割时就可以通过`切割出的中序数组`的`左区间大小`来确定`后序数组的切割点`
        int[] newLeftPostorder = Arrays.copyOfRange(postorder, 0, newLeftInorder.length);
        int[] newRightPostorder = Arrays.copyOfRange(postorder, newLeftInorder.length, postorder.length - 1);
        System.out.println("后序-左区间：" + Arrays.toString(newLeftPostorder));
        System.out.println("后序-右区间：" + Arrays.toString(newRightPostorder));
        // 7、递归处理左右子树
        root.left = buildTree(newLeftInorder, newLeftPostorder);
        root.right = buildTree(newRightInorder, newRightPostorder);

        return root;
    }

    public static void main(String[] args) {
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };
        int[] postorder = new int[] { 9, 15, 7, 20, 3 };

        TreeNode.levelPrint(
                new 从中序与后序遍历序列构造二叉树().buildTree(inorder, postorder));
    }
}
