/*
 * @Author: kaic
 * @Date: 2022-11-08 21:01:58
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 22:43:57
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 冒泡排序
 * 
 * 时间复杂度：O(n²)
 * 
 * 具有升级版本：【鸡尾酒排序 - 双向冒泡排序（先从左到右，再从右到左，一直来回，适合大部分数据已经有序的情况，可以有效减少排序次数）】
 */
public class BubbleSort {

    /**
     * 每轮排序从后往前确定排序完成的值
     */
    public static void bubbleSort(int[] array) {

        // 【第一次优化】
        // 添加flag值标记是否有进行过数值交换
        boolean flag = false;

        // 【第二次优化】
        // 添加有序边界
        // 对于列表：3 4 2 1 5 6 7 8
        // 后半部分 5 6 7 8 已经是有序的了，所以没有必要再去比较了
        int lastExchangeIndex = 0;
        // 无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;

        for (int i = 0; i < array.length - 1; i++) {
            flag = false;
            int temp = 0;

            // j < array.length - 1 - i // 减i是因为最后i个数字已经排序完成了
            for (int j = 0; j < sortBorder; j++) {
                // 从小到大排序 >
                // 从大到小排序 <
                if (array[j] > array[j + 1]) {
                    // 交换值
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    flag = true;
                    lastExchangeIndex = j;
                }
            }

            if (!flag) {
                break;
            }

            sortBorder = lastExchangeIndex;
        }
    }

    public static void main(String[] args) {
        // int[] array = new int[] { 4, 1, 6, 9, 3, 6, 7 };
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        bubbleSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
