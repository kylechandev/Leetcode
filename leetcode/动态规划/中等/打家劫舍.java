/*
 * @Author: kaic
 * @Date: 2023-05-07 09:26:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-07 23:05:43
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.中等;

/**
 * 198. 打家劫舍
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/house-robber/
 */
public class 打家劫舍 {

    public int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        // 偷第i家的最高金额
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        打家劫舍 demo = new 打家劫舍();

        int[] nums = new int[] { 1, 2, 3, 1 };
        System.out.println(demo.rob(nums));
    }
}
