/*
 * @Author: kaic
 * @Date: 2022-12-05 15:40:42
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 21:16:36
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.中等;

/**
 * 28. 找出字符串中第一个匹配项的下标 - KMP经典应用
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class 找出字符串中第一个匹配项的下标 {

    /**
     * KMP
     * 
     * @param haystack 原始串
     * @param needle   匹配串
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0; // 指向模式串
        for (int i = 0; i < haystack.length(); i++) {
            // 字符不匹配，模式串位置回退到上一个next数组的位置
            while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
                j = next[j - 1];
            }
            // 字符匹配，继续向前匹配
            if (needle.charAt(j) == haystack.charAt(i)) {
                j++;
            }

            if (j == needle.length()) { // j已经把模式串匹配完了，说明已经从原始串中找到模式串了
                // i指向的是原始串的当前位置，因为原始串已经匹配完成模式串了，所以现在i的位置是模式串的起始位置+模式串的长度，
                // 所以要获取原始串中模式串的起始位置，就用i再减模式串的长度即可
                return i - needle.length() + 1;
            }
        }

        return -1;
    }

    /**
     * next数组是`模式串分别取[0,j++]时的相等前后缀长度`（j++表示在各个位置为终点字符串）
     * 
     * getNext的过程就相当于是把j当作模式串，把i当作匹配串
     */
    private static void getNext(int[] next, String patternString) {
        // j指向前缀末尾，同时表示`最长相等前后缀`
        int j = 0;
        next[0] = 0;

        // i指向后缀末尾
        for (int i = 1; i < next.length; i++) {
            while (j > 0 && patternString.charAt(j) != patternString.charAt(i)) {
                // j回退
                j = next[j - 1];
            }

            if (patternString.charAt(j) == patternString.charAt(i)) {
                j++;
            }

            next[i] = j;
        }
    }

    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";
        System.out.println(strStr(haystack, needle));
    }
}
