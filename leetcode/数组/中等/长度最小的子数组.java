/*
 * @Author: kaic
 * @Date: 2022-12-01 10:02:56
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-01 14:43:59
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.中等;

/**
 * 209. 长度最小的子数组
 * 
 * 中等
 * 
 * 
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
 * ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 
 * 
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 
 * 
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class 长度最小的子数组 {

    /**
     * 暴力（超时）
     * 
     * 在暴力解法中，是一个for循环滑动窗口的起始位置，一个for循环为滑动窗口的终止位置，用两个for循环 完成了一个不断搜索区间的过程。
     * 
     * 时间复杂度：O(n²)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int length = Integer.MAX_VALUE;

        for (int slow = 0; slow < nums.length; slow++) {
            int sum = 0;
            for (int fast = slow; fast < nums.length; fast++) {
                sum += nums[fast];

                if (sum >= target) {
                    length = Math.min(length, fast - slow + 1);
                    break;
                }
            }
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }

    /**
     * 滑动窗口（双指针的一种应用）
     * 
     * 时间复杂度：O(n²)
     */
    public int minSubArrayLen2(int target, int[] nums) {
        // 只使用一个for循环，表示窗口终止位置
        int sum = 0;
        int start = 0;
        int length = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= target) { // 满足target
                length = Math.min(length, end - start + 1); // 取当前满足条件的窗口长度
                sum -= nums[start++]; // 向右移动起始位置，缩小窗口，并继续判断缩小后的窗口是否还满足条件
            }
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };

        System.out.println(new 长度最小的子数组().minSubArrayLen2(target, nums));
    }
}
