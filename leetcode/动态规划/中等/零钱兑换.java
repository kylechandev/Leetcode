/*
 * @Author: kaic
 * @Date: 2023-04-15 17:02:33
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-17 16:47:56
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.中等;

/**
 * 322. 零钱兑换
 * 
 * 中等
 * 
 * 
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的「最少的硬币个数」。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 「你可以认为每种硬币的数量是无限的。」
 * 
 * 
 * https://leetcode.cn/problems/coin-change/
 */
public class 零钱兑换 {

    public static int coinChange(int[] coins, int amount) {
        // 确定dp数组，dp[i]表示凑成i金额需要的硬币个数

        // 确定递推公式，dp[i] =

        return -1;
    }

    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 5 };
        int amount = 11;
        int n = coinChange(coins, amount);
        System.out.println(n);
    }
}
