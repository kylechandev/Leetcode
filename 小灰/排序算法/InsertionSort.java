/*
 * @Author: kaic
 * @Date: 2022-11-30 16:52:01
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 22:06:54
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 插入排序
 * 
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 
 * 
 * 基本思想是：
 * 把待排序的数组分成已排序和未排序两部分，初始的时候把第一个元素认为是已排好序的。
 * 从第二个元素开始，在「已排好序的子数组」中寻找到该元素合适的位置并插入到「已排序好的子数组」的这个位置。
 * 重复上述过程直到最后一个元素被插入有序子数组中。
 */
public class InsertionSort {

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i]; // 记录待排序插入的值
            int j = i; // 最后需要插入到「已排序数组」中的位置

            while (j > 0 && array[j - 1] > value) {
                array[j] = array[j - 1]; // 前面的大值后移（这里可以直接做后移操作，不需要交换两个位置的数值）
                j--; // 继续向前遍历，找合适的插入位置
            }

            array[j] = value; // 把待排序插入的值插入找到的合适的位置
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 8, 7 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        insertionSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
