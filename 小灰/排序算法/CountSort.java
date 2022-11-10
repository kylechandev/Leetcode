/*
 * @Author: kaic
 * @Date: 2022-11-09 21:15:43
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 08:59:33
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 计数排序 - `非`元素比较的排序
 * 
 * 优化后的计数排序属于【稳定排序】
 * 
 * 适用于【一定范围内的整数排序】，如果取值范围不大，性能甚至可以超过时间复杂度为O(logn)的排序算法
 * 
 * 【缺陷1】
 * 当数列最大和最小值差距过大时，并不适合用计数排序（会浪费很多空间，同时增加查询时间）
 * 
 * 【缺陷2】
 * 当数列元素不是整数时，也不适合用计数排序（因为都是通过数组下标的，数组下标只能是整数）
 */
public class CountSort {

    private static int getMin(int[] array) {
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    private static int getMax(int[] array) {
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    /**
     * 普通计数排序
     */
    public static void countSort(int[] array) {
        int min = getMin(array);
        int max = getMax(array);

        // 1、创建统计数组
        int[] countArray = new int[max - min + 1];
        // 2、统计每个独立元素出现的次数
        for (int i = 0; i < array.length; i++) {
            // 可以直接使用数组元素自增
            // array[index++]++;
            countArray[array[i] - min] = ++countArray[array[i] - min];
        }

        // 3、遍历统计数组，得到排序结果
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[index++] = i + min;
            }
        }
    }

    /**
     * 优化后的计数排序
     */
    public static int[] countSortV2(int[] array) {
        int min = getMin(array);
        int max = getMax(array);

        // 1、创建统计数组
        int[] countArray = new int[max - min + 1];
        // 2、统计每个独立元素出现的次数
        for (int i = 0; i < array.length; i++) {
            // 可以直接使用数组元素自增
            // array[index++]++;
            countArray[array[i] - min] = ++countArray[array[i] - min];
        }

        // --------- 优化点

        // 3、统计数组做变形，后面的元素等于前面的元素之和（表示元素最终排序的所在位置）
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        // 4、倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[--countArray[array[i] - min]] = array[i];
        }

        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 90, 94, 91, 98, 99, 90, 99, 93, 91, 92 };
        System.out.println("原始数组：" + Arrays.toString(array));
        countSort(array);
        System.out.println("排序数组V1：" + Arrays.toString(array));
        int[] array2 = new int[] { 90, 94, 91, 98, 99, 90, 99, 93, 91, 92 };
        System.out.println("排序数组V2：" + Arrays.toString(countSortV2(array2)));
    }
}
