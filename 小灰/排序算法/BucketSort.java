/*
 * @Author: kaic
 * @Date: 2022-11-10 09:02:29
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 10:09:47
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 * 
 * 可以做到线性时间复杂度：O(n)
 * 
 * 类似于计数排序，桶排序需要创建若干个桶来协助排序
 */
public class BucketSort {

    private static double getMax(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    private static double getMin(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static double[] bucketSort(double[] array) {
        // 1、确定桶的数量
        int bucketCount = 5;

        // 2、确定桶的区间跨度
        double min = getMin(array);
        double max = getMax(array);
        double gap = max - min;
        // 每个桶的元素大小范围（左闭右开）
        double length = gap / (bucketCount - 1);

        // 对于N个元素而言，令其取值范围为 gap（即 elementMaxValue-elementMinValue），
        // 易知其可构成 N-1 个区间长度length 为 gap/(N-1) 的左闭右开区间。
        // 由于是左闭右开区间，会使得最后一个区间的右端点无法取到，即排序元素中的最大值elementMaxValue无法被cover，故我们又在后面增加了一个同样长度的区间。
        // https://xyzghio.xyz/BucketSort/

        // 3、初始化桶
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketList.add(new LinkedList<>());
        }

        // 4、遍历原始数组，把元素放入对应的桶中
        for (int i = 0; i < array.length; i++) {
            // 计算桶号
            int num = (int) Math.floor((array[i] - min) / length); // 不减`min`其实也可以，`主要是对桶元素大小范围的取整操作`
            System.out.println("桶号：" + num);
            // 拿到桶
            LinkedList<Double> bucket = bucketList.get(num);
            // 把元素放入对应的桶中
            bucket.add(array[i]);
        }

        // 5、对每个桶进行排序
        for (int i = 0; i < bucketCount; i++) {
            LinkedList<Double> bucket = bucketList.get(i);
            if (bucket.size() > 1) {
                // 内部采用归并排序
                Collections.sort(bucket);
            }
        }

        // 排序完成后的数组
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            LinkedList<Double> bucket = bucketList.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                sortedArray[index++] = bucket.get(j);
            }
        }

        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[] { 4.12, 6.421, 0.0023, 3.0, 2.123, 8.122, 4.12, 10.09 };
        System.out.println("原始数组：" + Arrays.toString(array));
        System.out.println("排序数组：" + Arrays.toString(bucketSort(array)));
    }
}
