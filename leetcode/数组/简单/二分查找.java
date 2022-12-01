/*
 * @Author: kaic
 * @Date: 2022-11-30 23:15:00
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-01 09:15:36
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

/**
 * 二分查找
 * 
 * 简单
 * 
 * 
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的
 * target，如果目标值存在返回下标，否则返回 -1。
 * 
 * 
 * https://leetcode.cn/problems/binary-search/
 */
public class 二分查找 {

    /**
     * 递归解法
     * 
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (target > nums[mid]) {
            return search(nums, target, mid + 1, high);
        } else if (target < nums[mid]) {
            return search(nums, target, low, mid - 1);
        } else {
            return mid;
        }
    }

    /**
     * 迭代解法
     * 
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int search2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -1, 0, 3, 5, 9, 12 };
        System.out.println(new 二分查找().search2(nums, 9));
    }
}
