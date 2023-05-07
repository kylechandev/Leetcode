/*
 * @Author: kaic
 * @Date: 2023-05-07 08:53:27
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-07 09:05:03
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.简单;

/**
 * 746. 使用最小花费爬楼梯
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 */
public class 使用最小花费爬楼梯 {

    public int minCostClimbingStairs(int[] cost) {
        // 表示爬到第i层的最小花费
        int[] dp = new int[cost.length + 1];

        // 初始状态
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[cost.length];
    }

    public static void main(String[] args) {
        使用最小花费爬楼梯 demo = new 使用最小花费爬楼梯();

        int[] cost = new int[] { 10, 15, 20 };

        System.out.println(demo.minCostClimbingStairs(cost));
    }
}
