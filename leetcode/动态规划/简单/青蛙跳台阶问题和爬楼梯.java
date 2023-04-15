/*
 * @Author: kaic
 * @Date: 2023-04-14 22:57:22
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-15 21:01:37
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.简单;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题 / 70. 爬楼梯
 * 
 * 简单
 * 
 * 
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 */
public class 青蛙跳台阶问题和爬楼梯 {

    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }

        // 定义动态数组dp[i]表示爬到第i层有dp[i]中方法
        int[] dp = new int[n + 1];

        // 确定递推公式

        // 在到达第n层的上一步，我们只有两个选择，走一步，或者走两步。
        // 如果是走一步，我们需要先通过 f(n-1) 种方式到达 n-1 层
        // 如果是走两步， 我们需要通过 f(n-2) 种方式到达第 n-2 层
        // 所以综上有 f(n) = f(n-2) + f(n-1)

        // dp数组初始化
        // 1 2
        dp[1] = 1;
        dp[2] = 2;

        // 确定遍历顺序 - 从前往后（n需要依赖n-1和n-2）
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1000_000_007; // 题目要求：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 44;
        int count = climbStairs(n);

        System.out.println(count);
    }
}
