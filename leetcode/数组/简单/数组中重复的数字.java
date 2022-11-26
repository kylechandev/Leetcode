/*
 * @Author: kaic
 * @Date: 2022-11-24 17:14:27
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:34:42
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 
 * 简单
 * 
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * 
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
public class 数组中重复的数字 {

    /**
     * 借助数据结构Set
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }

        return -1;
    }

    /**
     * 原地交换
     * 
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solutions/96623/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-yua/
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int findRepeatNumber2(int[] nums) {
        int length = nums.length;
        int index = 0;

        while (index < length) {
            int num = nums[index];

            if (num == index) {
                index++;
                continue;
            } else if (num == nums[num]) {
                return num;
            } else {
                int temp = num;
                nums[index] = nums[num];
                nums[num] = temp;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 3, 1, 0, 2, 5, 3 };
        System.out.println(findRepeatNumber2(nums));
    }
}
