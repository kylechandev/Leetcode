/*
 * @Author: kaic
 * @Date: 2022-11-08 22:40:56
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 22:41:01
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] array) {

    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        quickSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
