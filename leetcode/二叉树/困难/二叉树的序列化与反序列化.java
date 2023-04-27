/*
 * @Author: kaic
 * @Date: 2023-04-27 22:05:20
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 22:45:18
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.困难;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import leetcode.二叉树.TreeNode;

/**
 * 297. 二叉树的序列化与反序列化
 * 
 * 困难
 * 
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 */
public class 二叉树的序列化与反序列化 {

    private String serializeAns = "";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        dfsS(root);

        return serializeAns.substring(0, serializeAns.length() - 1);
    }

    private void dfsS(TreeNode root) {
        if (root == null) {
            serializeAns += "null,";
            return;
        }

        serializeAns = serializeAns + String.valueOf(root.val) + ",";

        dfsS(root.left);
        dfsS(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] result = data.split(",");
        List<String> nodeList = new LinkedList<>(Arrays.asList(result));

        return dfsD(nodeList);
    }

    private TreeNode dfsD(List<String> nodeList) {
        if (nodeList.get(0).equals("null")) {
            nodeList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(nodeList.get(0)));
        nodeList.remove(0);

        root.left = dfsD(nodeList);
        root.right = dfsD(nodeList);

        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.demo(new Integer[] { 1, 2, 3, null, null, 4, 5 });

        二叉树的序列化与反序列化 demo = new 二叉树的序列化与反序列化();

        String serialize = demo.serialize(treeNode);
        System.out.println("序列化：" + serialize);

        TreeNode deserialize = demo.deserialize(serialize);
        System.out.println("反序列化：");
        TreeNode.levelPrint(deserialize);
    }
}
