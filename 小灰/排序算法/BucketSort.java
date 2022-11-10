/*
 * @Author: kaic
 * @Date: 2022-11-10 09:02:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 09:02:32
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 桶排序
 */
public class BucketSort {

    public static void bucketSort(int[] array) {

    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 3, 2, 6, 5, 7, 8, 9, 10, 0 };
        System.out.println("原始数组：" + Arrays.toString(array));
        bucketSort(array);
        System.out.println("排序数组：" + Arrays.toString(array));
    }
}
