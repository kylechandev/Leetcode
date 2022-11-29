/*
 * @Author: kaic
 * @Date: 2022-11-29 13:45:41
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-29 14:24:57
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.二叉树.简单;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import leetcode.二叉树.TreeNode;

/**
 * 501. 二叉搜索树中的众数
 * 
 * 简单
 * 
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 
 * 假定 BST 满足如下定义：
 * 
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * 
 * 
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/
 */
public class 二叉搜索树中的众数 {

    /**
     * 按普通树来暴力搜索
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }

        // 计算每个值的频率
        Map<Integer, Integer> map = new HashMap<>();
        traverval(root, map);

        // 返回结果
        List<Integer> result = new ArrayList<>();

        // 按频率从高到低排序
        List<Map.Entry<Integer, Integer>> mapList = map.entrySet()
                .stream()
                .sorted((a1, a2) -> a2.getValue().compareTo(a1.getValue()))
                .collect(Collectors.toList());
        // 把频率最高的添加进来
        result.add(mapList.get(0).getKey());

        for (int i = 1; i < mapList.size(); i++) {
            if (mapList.get(i).getValue() == mapList.get(i - 1).getValue()) {
                // 把和最大频率相同的key也添加进来
                result.add(mapList.get(i).getKey());
            } else {
                break;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void traverval(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }

        traverval(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        traverval(root.right, map);
    }

    /**
     * 按二叉搜索树的特性
     */
    public int[] findMode1(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }

        result = new ArrayList<>();

        searchBST(root);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private TreeNode preNode = null;
    private int count;
    private int maxCount;
    private List<Integer> result;

    private void searchBST(TreeNode root) {
        if (root == null) {
            return;
        }

        searchBST(root.left);

        if (preNode == null) {
            // 第一次出现
            count = 1;
        } else if (preNode.val == root.val) {
            // 再次出现
            count++;
        } else {
            // 新值出现
            count = 1;
        }
        preNode = root;

        if (count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(root.val);
        } else if (count == maxCount) {
            result.add(root.val);
        }

        searchBST(root.right);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new 二叉搜索树中的众数().findMode1(TreeNode.demo(new Integer[] { 1, null, 2, null, null, 2 }))));
    }
}
