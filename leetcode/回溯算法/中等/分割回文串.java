/*
 * @Author: kaic
 * @Date: 2023-04-20 23:32:21
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-22 09:42:08
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.回溯算法.中等;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 
 * 中等
 * 
 * 
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * 
 * 
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 
 * 
 * https://leetcode.cn/problems/palindrome-partitioning/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class 分割回文串 {

    private List<List<String>> result = new ArrayList<>();

    /**
     * 分割
     * 
     * 通过「https://zhuanlan.zhihu.com/p/69777001」理解
     * 再看「https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html#java」
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.isBlank()) {
            return result;
        }

        dfs(s, new ArrayList<>(), 0);

        return result;
    }

    /**
     * DFS
     * 
     * @param s          原始待分割的字符串
     * @param list       本次分割结果
     * @param startIndex 从哪个位置开始
     */
    private void dfs(String s, ArrayList<String> list, int startIndex) {
        // 递归终止条件 - DFS
        if (startIndex >= s.length()) {
            // 找到一个分割结果了
            result.add(new ArrayList<>(list));
            return;
        }

        // 单层搜索逻辑
        for (int end = startIndex; end < s.length(); end++) {
            if (isPalindroom(s, startIndex, end)) {
                // 添加当前回文串到 分割list 中
                list.add(s.substring(startIndex, end + 1));
                // 开始递归
                dfs(s, list, end + 1); // 从下一个位置开始继续分割
                // 回溯，撤销上一次操作，从而继续for循环寻找更长的回文串
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 判断是否为回文数
     */
    private boolean isPalindroom(String s, int start, int end) {
        int left = start;
        int right = end;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(new 分割回文串().partition(s));
    }
}
