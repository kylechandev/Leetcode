/*
 * @Author: kaic
 * @Date: 2022-11-11 14:47:17
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-11 16:56:46
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.删去k个数字后的最小值;

import java.util.Arrays;

/**
 * 删去k个数字后的最小值 - 贪心算法
 * 
 * 给出一个整数，从该整数中去掉k个数字，要求剩下的数字形成的新整数尽可能小，
 * 其中整数的长度大于或等于k，给出的整数的大小可以超过long类型的数字范围
 * 
 * 例如给出一个整数：1593212，删去3个数字（593），得到的最小新整数是1212
 * 
 * 
 * 依次求得局部最优解，最终得到全局最优解的思想，叫作贪心算法
 */
public class Solution {

    /**
     * 计算删除k个数后的最小整数
     * 
     * @param number 原数字
     * @param k      删除的个数
     */
    public static int getMinimunNumber(int number, int k) {
        int numberCount = numberCount(number);

        if (numberCount <= k) {
            return 0;
        }

        // 转化成数字数组
        int[] numberArray = number2IntArray(number, numberCount);

        boolean cutted = false;

        int times = k;
        while (times > 0) {
            for (int i = 0; i < numberArray.length - 1; i++) {
                if (numberArray[i] > numberArray[i + 1]) {
                    // 如果这个数字的后一个数字比它小，那么可以删除这个数字
                    numberArray = deleteArrayElementAt(numberArray, i);
                    cutted = true;
                    break;
                }
            }

            // 依此求解
            times--;
        }

        if (!cutted) {
            // 如果没有删除任何数字，那么移除后k个数
            for (int i = k; i > 0; i--) {
                numberArray = deleteArrayElementAt(numberArray, i);
            }
        }

        // 如果数字被全部删除了，则返回0
        if (numberArray.length == 0) {
            return 0;
        }

        return getNumberFromArray(numberArray);
    }

    /**
     * 删除数组中指定位置的元素
     */
    private static int[] deleteArrayElementAt(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }

        // 返回新的数组
        return Arrays.copyOf(array, array.length - 1); // 把原数组的大小减1
    }

    /**
     * 把整型数组的数字拼成一个大数
     */
    private static int getNumberFromArray(int[] numberArray) {
        int length = numberArray.length;
        int num = 0;
        for (int i = 0; i < length; i++) {
            num += numberArray[i] * Math.pow(10, length - i - 1);
        }

        System.out.println("拼接数字：" + num);

        return num;
    }

    /**
     * 把一个数字的每个数字放入数组中
     * 
     * @param number 数字
     * @param count  数字个数
     */
    private static int[] number2IntArray(int number, int count) {
        int[] array = new int[count];
        int num = number;
        for (int i = count - 1; i >= 0; i--) {
            int d = num % 10;
            array[i] = d;
            num /= 10;
        }

        return array;
    }

    /**
     * 判断数字是几位数
     */
    private static int numberCount(int number) {
        int count = 0;

        int num = number;
        while (num >= 1) {
            num /= 10;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        // int a = 1593212;
        int a = 1000200010;
        int k = 1;
        System.out.println(a + "删除" + k + "个数后的最小整数是：" + getMinimunNumber(a, k));
    }
}
