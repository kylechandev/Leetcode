/*
 * @Author: kaic
 * @Date: 2022-11-30 17:09:42
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 22:59:06
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 归并排序
 * https://labuladong.github.io/algo/2/21/41/
 * https://blog.csdn.net/weixin_45970271/article/details/124460317
 * 
 * 归并排序利用的是分解问题的思路（分治算法）
 * 
 * 二叉树思想
 * 
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(logn)
 */
public class MergeSort {

    // 辅助数组
    private int[] temp;

    public void mergeSort(int[] nums) {
        // 先给辅助数组开辟内存空间，避免后续频繁开辟
        temp = new int[nums.length];
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        // 防止int溢出，等价于(low+hight)/2 - 指low+hight可能int溢出
        int mid = low + (high - low) / 2;

        // 先对左半部分数组 nums[lo..mid] 排序
        sort(nums, low, mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        sort(nums, mid + 1, high);

        // 上面完成分割，现在已经分割成一个一个数值了
        // https://zhuanlan.zhihu.com/p/124356219
        // 图 - https://pic4.zhimg.com/80/v2-2958d4f3d9dd9156f1b5dca6788fe8a7_1440w.webp

        // 然后下面开始归并到一起，使之有序

        // 将两部分有序数组合并成一个有序数组
        merge(nums, low, mid, high);
    }

    /**
     * 合并两个有序数组[low, mid], [mid+1,high]
     */
    private void merge(int[] nums, int low, int mid, int high) {
        // 合并方式一：
        for (int i = 0; i < nums.length; i++) {
            // 先把 nums[lo..hi] 复制到辅助数组中
            // 以便合并后的结果能够直接存入 nums
            temp[i] = nums[i];
        }
        // 双指针法合并
        int i = low; // [low, mid]左半边数组从头开始遍历
        int j = mid + 1; // [mid+1,high]右半边数组从头开始遍历
        for (int k = low; k <= high; k++) { // 开始遍历子数组位置
            if (i == mid + 1) { // 左半边数组已全部被合并完成
                nums[k] = temp[j++]; // 那么现在只能继续依次把右半边数组剩下的值合并上
            } else if (j == high + 1) { // 右半边数组已全部被合并完成
                nums[k] = temp[i++]; // 那么现在只能继续依次把左半边数组剩下的值合并上
            } else if (temp[i] <= temp[j]) { // 左半边数组的值 小于 右半边数组的值
                nums[k] = temp[i++]; // 使用小值的那个，并让其下标向后移动以便继续遍历
            } else if (temp[i] > temp[j]) { // 左半边数组的值 大于 右半边数组的值
                nums[k] = temp[j++]; // 使用小值的那个，并让其下标向后移动以便继续遍历
            }
        }

        // 合并方式二：
        // int i = low;
        // int j = mid + 1;
        // int k = 0;

        // while (i <= mid && j <= high) {
        // temp[k++] = nums[i] > nums[j] ? nums[j++] : nums[i++];
        // }
        // while (i <= mid) {
        // temp[k++] = nums[i++];
        // }
        // while (j <= high) {
        // temp[k++] = nums[j++];
        // }

        // for (i = 0; i < k; i++) {
        // // 把数据复制回原数组
        // nums[low + i] = temp[i];
        // }
    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 8, 7 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        new MergeSort().mergeSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
