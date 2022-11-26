/*
 * @Author: kaic
 * @Date: 2022-11-07 14:27:58
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-26 14:50:52
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 * 
 * 简单
 * 
 * 给你一个 升序排列 的数组 nums ，请 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。
 * 更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。将最终结果插入 nums 的前 k 个位置后返回 k 。
 * 
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class 删除有序数组中的重复项 {

    /**
     * 双指针解法
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solutions/575549/shua-chuan-lc-jian-ji-shuang-zhi-zhen-ji-2eg8/
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0; // i指向有效数组的最后一位
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                // 当i和j所指向位置的数不相等时，说明出现新数，更新i
                nums[++i] = nums[j];
            }
        }

        // 返回有效数组长度
        return i + 1; // 因为这里i表示的是有效数组的最后一位下标，所以最终return有效数组长度时还需要再+1
    }

    /**
     * 通用解法
     * 
     * @param k 重复数字最多保留k位
     */
    public static int removeDuplicates(int[] nums, int k) {
        int idx = 0; // 有效数组待插入的位置

        for (int num : nums) {
            // idx < k 防止有数字原始的重复数量不足k个
            // nums[idx - k] 让num和有效数组的前k个数取比较（因为每个数字可以重复k次），不同则插入
            if (idx < k || nums[idx - k] != num) {
                nums[idx++] = num;
            }
        }

        return idx; // 因为这里idx表示接下来待插入的位置，而不是有效数组的最后一个位置下标，所以return时不用再+1了
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int a = removeDuplicates(nums, 1);
        System.out.println(a);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, a)));
    }
}
