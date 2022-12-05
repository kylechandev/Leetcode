/*
 * @Author: kaic
 * @Date: 2022-12-05 15:43:13
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 21:36:27
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.简单;

/**
 * 459. 重复的子字符串
 * 
 * 简单
 * 
 * 
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 
 * 
 * 例如：
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 * 
 * 
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 * 
 * 
 * https://leetcode.cn/problems/repeated-substring-pattern/
 */
public class 重复的子字符串 {

    /**
     * KMP解法
     */
    public static boolean repeatedSubstringPattern(String s) {
        // abab
        // 0012
        // abcabcabcabc
        // 000123123456

        int length = s.length();
        // 1、计算next数组
        int[] next = new int[length];
        getNext(next, s);

        // 12 % (12-6)
        // 数组长度 % (数组长度 - 最长相等前后缀的长度)
        if (next[length - 1] > 0 && (length % (length - next[length - 1])) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static void getNext(int[] next, String s) {
        // abcd
        // 0000
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < next.length; i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
    }

    /**
     * 移动匹配
     */
    public static boolean repeatedSubstringPattern2(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s)
                // 复制一份
                .append(s)
                // 首尾去除一个字符，避免搜索到原始的s
                .deleteCharAt(0)
                .deleteCharAt(sb.length() - 1);

        return sb.toString().contains(s);
    }

    public static void main(String[] args) {
        String s = "abab";
        System.out.println(repeatedSubstringPattern(s));
    }
}
