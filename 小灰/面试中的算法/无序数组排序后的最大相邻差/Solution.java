/*
 * @Author: kaic
 * @Date: 2022-11-10 19:16:15
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 22:23:05
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.无序数组排序后的最大相邻差;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import 小灰.排序算法.HeapSort;

/**
 * 无序数组排序后的最大相邻差
 */
public class Solution {

    /**
     * 方法1
     * 
     * 先用O(logn)排序，再遍历排序后的数组
     */
    public static int getMaxSortedDistance1(int[] array) {
        // 先排序
        HeapSort.heapSort(array);
        // 再遍历
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            int d = array[i] - array[i - 1];
            if (d > max) {
                max = d;
            }
        }

        return max;
    }

    /**
     * 方法2
     * 
     * 使用计数排序的思想
     * 
     * 有问题，如果数值分散过开，会浪费很多的空间
     */
    public static int getMaxSortedDistance2(int[] array) {

        // 计算最大最小值
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 辅助计数数组的长度
        int count = max - min + 1;
        // 偏移量（以最小值作为偏移量，作为计数数组的下标）
        int d = min;

        // 遍历原数组，开始计数
        int[] countArray = new int[count];
        for (int i = 0; i < array.length; i++) {
            ++countArray[array[i] - d];
        }

        // 统计最大连续出现0的次数
        int zeroLength = 0;
        int maxLength = 0;
        for (int i = 0; i < countArray.length; i++) {
            // 统计0的数量
            if (countArray[i] == 0) {
                zeroLength++;
            } else {
                zeroLength = 0;
            }

            // 缓存记录的0最大连续出现的次数
            if (zeroLength > maxLength) {
                maxLength = zeroLength;
            }
        }

        // 结果值再加1就是相邻元素的最大差值
        return maxLength + 1;
    }

    /**
     * 方法4
     * 
     * 采用 桶排序 的思想
     * 
     * @param <T>
     */
    public static <T> int getMaxSortedDistance3(int[] array) {
        if (array.length <= 1) {
            return 0;
        }

        // 计算最大最小值
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 确定桶的跨度
        int gap = max - min;
        // 确定桶的数量
        int bucketCount = array.length / 2;
        // 确定每个桶的大小
        int bucketSize = gap / (bucketCount - 1);

        // 初始化桶
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<LinkedList<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketList.add(new LinkedList<>());
        }

        // 开始遍历数组，把元素塞入对应的桶中
        for (int i = 0; i < array.length; i++) {
            // 计算桶的下标
            int num = (array[i] - min) / bucketSize;
            // 获取到桶
            LinkedList<Integer> bucket = bucketList.get(num);
            // 塞入桶中
            bucket.add(array[i]);
        }

        // 计算每个桶的最大值和最小值
        ArrayList<Map<String, Integer>> result = new ArrayList<Map<String, Integer>>();
        bucketList.forEach(new Consumer<LinkedList<Integer>>() {
            @Override
            public void accept(LinkedList<Integer> value) {
                if (!value.isEmpty()) {
                    int min = Collections.min(value);
                    int max = Collections.max(value);
                    Map<String, Integer> map = Map.of("min", min, "max", max);
                    result.add(map);
                    System.out.println(map);
                }
            }
        });

        // 遍历所有的桶，找到最大差值（当前桶的最大值和相邻右非空桶的最小值的差值）
        int maxDistance = 0;
        Integer leftMax = result.get(0).get("max");
        for (int i = 1; i < result.size(); i++) {
            Map<String, Integer> right = result.get(i);
            Integer rightMin = right.get("min");
            if (rightMin == null) {
                continue;
            }
            Integer rightMax = right.get("max");

            // 更新最大差值
            if (rightMin - leftMax > maxDistance) {
                maxDistance = rightMin - leftMax;
            }

            // 准备比较下个桶
            leftMax = rightMax;
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 6, 3, 4, 5, 10, 8 };
        System.out.println(getMaxSortedDistance1(array));
        System.out.println(getMaxSortedDistance2(array));
        System.out.println(getMaxSortedDistance3(array));
    }
}
