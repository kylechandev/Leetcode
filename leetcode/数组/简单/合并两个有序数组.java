/*
 * @Author: kaic
 * @Date: 2022-10-29 13:15:37
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 23:15:34
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.简单;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 
 * 简单
 * 
 * 数组、尾部指针
 * 
 * https://leetcode.cn/problems/merge-sorted-array/
 * 
 * Created by kaic on 2022/10/29
 */
public class 合并两个有序数组 {

    private static void merge(int[] nums1, int[] nums2, int m, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int current = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[current--] = nums1[p1--];
            } else {
                nums1[current--] = nums2[p2--];
            }
        }

        while (p2 >= 0) {
            nums1[current--] = nums2[p2--];
        }

        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 3, 5, 0, 0, 0 };
        int[] array2 = new int[] { 2, 4, 6 };
        merge(array1, array2, 3, 3);
    }
}
