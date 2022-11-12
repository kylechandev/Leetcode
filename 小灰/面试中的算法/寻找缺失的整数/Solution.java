/*
 * @Author: kaic
 * @Date: 2022-11-12 11:01:40
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-12 11:22:31
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.寻找缺失的整数;

import java.util.HashMap;
import java.util.Map;

import 小灰.排序算法.HeapSort;

/**
 * 寻找缺失的整数
 * 
 * 在一个无序数组里有99个不重复的正整数，范围是1～100，唯独缺少1个1～100中的整数。如何找出这个缺失的整数？
 */
public class Solution {

    /**
     * 采用HashMap缓存key，再遍历原数组删除key，剩下的一个key就是缺失的数
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int findLostNum1(int[] array, int from, int to) {
        // O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = from; i <= to; i++) {
            map.put(i, null);
        }

        // O(n)
        for (int i = 0; i < array.length; i++) {
            map.remove(array[i]);
        }

        // 返回剩下的一个
        return map.keySet().iterator().next();
    }

    /**
     * 先给原数组进行排序，再遍历原数组找到不相邻的数
     * 
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public static int findLostNum2(int[] array, int from, int to) {
        // O(logn)
        HeapSort.heapSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] + 1 != array[i + 1]) {
                return array[i] + 1;
            }
        }

        return array[array.length - 1] + 1;
    }

    /**
     * 先算出from到to的累加和，然后依此减去array中的每个数字，剩下的就是丢失的数
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int findLostNum3(int[] array, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += i;
        }

        for (int i = 0; i < array.length; i++) {
            sum -= array[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int from = 1;
        int to = 9;
        System.out.println("缺失的数1：" + findLostNum1(array, from, to));
        System.out.println("缺失的数2：" + findLostNum2(array, from, to));
        System.out.println("缺失的数2：" + findLostNum3(array, from, to));
    }
}
