/*
 * @Author: kaic
 * @Date: 2023-04-17 16:49:52
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 21:22:28
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.动态规划.中等;

/**
 * 518. 零钱兑换2
 * 
 * 中等
 * 
 * 
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回「可以凑成总金额的 硬币组合数」。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 「假设每一种面额的硬币有无限个。」
 * 题目数据保证结果符合 32 位带符号整数。
 * 
 * https://leetcode.cn/problems/coin-change-ii/
 */
public class 零钱兑换2 {

    /**
     * 思路：典型背包问题（钱币数量不限，完全背包问题）
     */
    public static int change(int amount, int[] coins) {

        // 确定dp数组，dp[i]表示凑成i金额需要的硬币组合数

        return 0;
    }

    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 5 };
        int amount = 5;
        int n = change(amount, coins);
        System.out.println(n);

        String a = "a"; // 在字符串常量池中
        String b = new String("a"); // 是直接在堆上创建的一个新对象
        // b.intern(); // 可以把new出来的String放入常量池中
        System.out.println(a == b); // 比较引用
        System.out.println(a.equals(b)); // 重写了，比较的是值

        Thread aa = new Thread() {
            @Override
            public void run() {
                System.out.println("哈哈");
            };
        };
        aa.start();
    }
}
