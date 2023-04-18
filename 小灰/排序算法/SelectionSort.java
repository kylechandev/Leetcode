/*
 * @Author: kaic
 * @Date: 2022-11-30 16:39:32
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 21:57:43
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
/*
 * @Author: kaic
 * @Date: 2022-11-30 16:39:32
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-30 16:49:55
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 选择排序
 * 
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 
 * 基本思想是：
 * 首先在未排序的数列中找到最小(or最大)元素，
 * 然后将其存放到数列的起始位置；
 * 接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，
 * 然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {
        int min; // 未排序区间的最小值下标
        for (int i = 0; i < array.length - 1; i++) {
            min = i;

            for (int j = i + 1; j < array.length; j++) {
                // 记录未排序区间的最小值的下标
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            // 未排序区间的最小值位置和i位置(已排序区间的最后位置)进行交换
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 8, 7 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        selectionSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
