/*
 * @Author: kaic
 * @Date: 2022-12-05 10:49:35
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 14:44:35
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.简单;

/**
 * 541. 反转字符串2
 * 
 * 简单
 * 
 * 
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 
 * 
 * 例如：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 
 * 
 * https://leetcode.cn/problems/reverse-string-ii/
 */
public class 反转字符串2 {

    /**
     * 写大段逻辑处理（最一开始还多用了一个count计数器来辅助，后来优化后去掉了）
     */
    public static String reverseStr(String s, int k) {
        char[] ss = s.toCharArray();

        int length = ss.length;
        int doubleK = 2 * k;

        int preIndex = 0;
        for (int i = 0; i < length; i++) {
            // 累计个数
            int total = i - preIndex + 1;

            if (total == doubleK || i + 1 >= length) {
                // 准备反转
                int left = 0;
                int right = 0;

                if (total < k) {
                    // 剩余字符少于 k 个，则将剩余字符全部反转
                    left = preIndex;
                    right = i;
                } else {
                    // 剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符
                    left = preIndex;
                    right = preIndex + k - 1;
                }

                // 交换位置
                char temp;
                while (left < right) {
                    temp = ss[left];
                    ss[left] = ss[right];
                    ss[right] = temp;

                    left++;
                    right--;
                }

                // 重置临时变量
                preIndex = i + 1;
            }
        }

        return String.valueOf(ss);
    }

    /**
     * 区间法
     */
    public static String reverseStr2(String s, int k) {
        char[] ss = s.toCharArray();

        int length = ss.length;
        int doubleK = 2 * k;

        for (int i = 0; i < length; i += doubleK) { // 让i每次移动2k个位置
            int endIndex = i + k;
            if (endIndex < length) {
                // 够k个数
                reverse(ss, i, endIndex - 1);
            } else {
                // 不够k个数
                reverse(ss, i, length - 1);
            }
        }

        return String.valueOf(ss);
    }

    private static void reverse(char[] ss, int from, int to) {
        int left = from;
        int right = to;
        char temp;
        while (left < right) {
            temp = ss[left];
            ss[left] = ss[right];
            ss[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStr2(s, k));
    }
}
