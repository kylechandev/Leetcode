/*
 * @Author: kaic
 * @Date: 2022-11-25 08:55:30
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:34:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 448. 找到所有数组中消失的数字
 * 
 * 简单
 * 
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * 
 * 示例1:
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 
 * 示例2:
 * 输入：nums = [1,1]
 * 输出：[2]
 * 
 * https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 */
public class 找到所有数组中消失的数字 {

    /**
     * 原地hash交换数组
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int length = nums.length;

        // 1、原地交换
        for (int i = 0; i < length; i++) {
            int num = nums[i];

            while (num >= 1 && num <= length && nums[i] != nums[num - 1]) {
                // 交换nums[i]和nums[nums[i]-1]
                int temp = nums[num - 1];
                nums[num - 1] = num;
                nums[i] = temp;

                num = temp;
            }
        }

        // 2、1-n遍历
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }

    /**
     * 借助数据结构Set
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 3, 2, 7, 8, 2, 3, 1 };
        System.out.println(findDisappearedNumbers2(nums));
    }
}
