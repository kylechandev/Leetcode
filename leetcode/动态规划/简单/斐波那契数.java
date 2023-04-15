/*
 * @Author: kaic
 * @Date: 2023-04-15 16:07:05
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-15 18:09:57
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.简单;

/**
 * 509. 斐波那契数
 * 
 * 简单
 * 
 * 
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * 
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * 
 * 
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class 斐波那契数 {

    /*
     * 动态规划五部曲：
     * 
     * 确定dp(一维/二维)数组（dp table）以及下标的含义 - 作用：保存递归的结果（用来保存历史记录）
     * 确定递推公式
     * dp数组如何初始化
     * 确定遍历顺序
     * 举例推导dp数组
     */

    /**
     * 递归解法
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 动态规划解法
     */
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }

        // dp[i]表示第i个斐波那契数
        // 确定递推公式：dp[i] = dp[i-1] + dp[i-2]
        // dp数组初始化：dp[0] = 0; dp[1] = 1

        // int[] dp = new int[n + 1];
        // dp[0] = 0;
        // dp[1] = 1;

        // for (int i = 2; i <= n; i++) {
        // dp[i] = dp[i - 1] + dp[i - 2];
        // }

        // return dp[n];

        // 优化版本
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }

        return dp[1];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(fib2(n));
    }
}
