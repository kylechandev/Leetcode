/*
 * @Author: kaic
 * @Date: 2023-04-20 23:13:27
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-20 23:20:34
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数学.简单;

/**
 * 9. 回文数
 * 
 * 简单
 * 
 * 
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * 
 * https://leetcode.cn/problems/palindrome-number/solutions/
 */
public class 回文数 {

    /**
     * 反转数字本身
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        // 反转后的数字
        int y = 0;

        // 开始反转数字
        int temp = x;
        while (temp > 0) {
            // 不断取x的个位数（最低位），给y（从最高位依次给）
            y = y * 10 + temp % 10;
            temp /= 10;
        }

        return y == x;
    }

    public static void main(String[] args) {
        int num = 121;
        if (isPalindrome(num)) {
            System.out.println(num + "是回文数");
        } else {
            System.out.println(num + "不是回文数");
        }
    }
}
