/*
 * @Author: kaic
 * @Date: 2022-12-05 15:27:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 15:38:42
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.简单;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * 
 * 简单
 * 
 * 
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 
 * 
 * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 */
public class 左旋转字符串 {

    /**
     * 空间复杂度O(n)
     */
    public static String reverseLeftWords(String s, int n) {
        int length = s.length();
        char[] ss = s.toCharArray();

        char[] result = new char[length];
        int size = 0;
        for (int i = n; i < ss.length; i++) {
            result[size++] = ss[i];
        }
        for (int i = 0; i < n; i++) {
            result[size++] = ss[i];
        }

        return String.valueOf(result);
    }

    /**
     * 空间复杂度O(1)
     */
    public static String reverseLeftWords2(String s, int n) {
        // 先局部反转[0,n-1]和[n,size-1]
        // 再整体反转[0,size-1]

        char[] result = s.toCharArray();
        reverse(result, 0, n - 1);
        reverse(result, n, s.length() - 1);
        reverse(result, 0, s.length() - 1);

        return String.valueOf(result);
    }

    private static void reverse(char[] result, int from, int to) {
        int left = from;
        int right = to;
        while (left < right) {
            char leftChar = result[left];
            result[left] = result[right];
            result[right] = leftChar;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int n = 2;
        System.out.println(reverseLeftWords2(s, n));
    }
}
