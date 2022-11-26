/*
 * @Author: kaic
 * @Date: 2022-11-13 10:26:51
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:35:38
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数学;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/two-sum/
 * 
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 
 */
public class 两数之和 {

    /**
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
     */
    public static int[] sum(int[] nums, int target) {
        // 差值, 下标
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        // 开始遍历数组
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (map.get(num) != null) {
                return new int[] { map.get(num), i };
            } else {
                map.put(target - num, i);
            }
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 7, 11, 15 };
        int target = 18;
        System.out.println("两数下标：" + Arrays.toString(sum(nums, target)));
    }
}
