/*
 * @Author: kaic
 * @Date: 2022-11-09 20:29:38
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-09 21:09:14
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;

/**
 * 堆排序 - O(nlogn)
 */
public class HeapSort {

    public static void heapSort(int[] array) {
        // 先根据源数组构建出二叉堆
        buildHeap(array);

        // 循环交换集合尾部元素到堆顶，并调节堆产生新的堆顶
        // 因为是依此首尾交换，所以总共的交换次数就是`array.length - 1`，并且每次交换并下浮后，最后的那个数就是已经排序好的数
        // for (int i = array.length - 1; i > 0; i--) // 从后往前扫
        for (int i = 0; i < array.length - 1; i++) {
            int temp = array[0];
            int last = array[array.length - 1 - i];
            array[0] = last;
            array[array.length - 1 - i] = temp;

            // 执行下浮调整二叉堆
            downAdjust(array, 0, array.length - 1 - i);
        }
    }

    /**
     * 下浮
     * 
     * @param array       堆
     * @param parentIndex 需要下浮的父元素的位置
     * @param length      堆的有效大小
     */
    private static void downAdjust(int[] array, int parentIndex, int length) {
        int tempParent = array[parentIndex];

        // 找到子节点
        int childIndex = getLeftChildIndex(parentIndex);

        // 准备下浮
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                // 右子节点更大，使用右子节点
                childIndex++;
            }

            if (array[childIndex] <= tempParent) {
                // 子节点更小，不用下浮了
                break;
            }

            // 开始下浮
            array[parentIndex] = array[childIndex];

            // 计算新的位置
            parentIndex = childIndex;
            childIndex = getLeftChildIndex(childIndex);
        }

        // 下浮结束
        array[parentIndex] = tempParent;
    }

    /**
     * 构建二叉堆
     * 
     * @param array 原始数组
     */
    private static void buildHeap(int[] array) {
        // 从后往前 依此下浮 非叶子节点
        for (int i = getParentIndex(array.length - 1); i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    /**
     * 获取父节点的位置
     * 
     * @param childIndex 子节点的位置
     */
    private static int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * 获取子节点的位置
     * 
     * @param parentIndex 父节点的位置
     */
    private static int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 3, 2, 6, 5, 7, 8, 9, 10, 0 };
        System.out.println("原始数组：" + Arrays.toString(array));
        heapSort(array);
        System.out.println("排序数组：" + Arrays.toString(array));
    }
}
