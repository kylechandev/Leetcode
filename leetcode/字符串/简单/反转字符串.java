/*
 * @Author: kaic
 * @Date: 2022-11-13 22:41:23
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 10:47:56
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.简单;

import java.util.Arrays;

/**
 * 344. 反转字符串
 * 
 * 简单
 * 
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 
 * https://leetcode.cn/problems/reverse-string/
 */
public class 反转字符串 {

    /**
     * 常规思路
     * 
     * 时间复杂度：O(1)
     */
    public static void reverseString(char[] s) {
        int length = s.length;

        if (length < 2) {
            return;
        }

        char temp;
        for (int i = 0; i < length / 2; i++) {
            temp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = temp;
        }
    }

    /**
     * 用双指针的思想 - 多发散思维！不要总是用固有的死板思路！
     * 
     * 时间复杂度：O(1)
     */
    public static void reverseString2(char[] s) {
        if (s.length < 2) {
            return;
        }

        // 头尾指针
        int left = 0;
        int right = s.length - 1;

        char temp;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = new char[] { 'c', 'k' };
        reverseString2(s);
        System.out.println(Arrays.toString(s));
    }
}
