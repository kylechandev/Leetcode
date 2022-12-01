/*
 * @Author: kaic
 * @Date: 2022-12-01 09:16:40
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-01 09:45:32
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

/**
 * 27. 移除元素
 * 
 * 简单
 * 
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 
 * 
 * https://leetcode.cn/problems/remove-element/
 */
public class 移除元素 {

    /**
     * 暴力
     * 
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int removeElement(int[] nums, int val) {
        int size = nums.length;

        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                // 发现需要移除的元素，就将数组从此位置开始集体向前移动一位
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                i--;
                size--;
            }
        }

        return size;
    }

    /**
     * 双指针法（快慢指针法）
     * 
     * 快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
     * 慢指针：指向更新 新数组下标的位置
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeElement2(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }

        return slowIndex;
    }

    /**
     * 双指针法优化（改变元素的相对位置）
     * 
     * 例如序列 [1,2,3,4,5]，当 val 为 1 时，我们需要把每一个元素都左移一位。
     * 注意到题目中说：「元素的顺序可以改变」。
     * 实际上我们可以直接将最后一个元素 5 移动到序列开头，取代元素 1，得到序列 [5,2,3,4]
     */
    public int removeElement3(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right--];
            } else {
                left++;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 3, 2, 3, 2, 3 };
        int val = 3;
        System.out.println(new 移除元素().removeElement3(nums, val));
    }
}
