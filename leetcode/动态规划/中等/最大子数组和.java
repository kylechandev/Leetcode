/*
 * @Author: kaic
 * @Date: 2023-05-07 09:09:36
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-07 09:18:44
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.中等;

/**
 * 53. 最大子数组和
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/maximum-subarray/
 */
public class 最大子数组和 {

    public int maxSubArray(int[] nums) {
        // 表示以第i个整数结尾的子数组的最大值
        int[] dp = new int[nums.length];

        // 两种情况，
        // 1. 和第i-1个整数结尾的子数组相连
        // 1. 和第i-1个整数结尾的子数组不相连（就是单独以第i个整数作为子数组）

        // 初始化
        dp[0] = nums[0];

        int maxValue = nums[0];
        // 状态转移方程
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxValue = Math.max(maxValue, dp[i]);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        最大子数组和 demo = new 最大子数组和();

        int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        System.out.println(demo.maxSubArray(nums));
    }
}
