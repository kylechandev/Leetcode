/*
 * @Author: kaic
 * @Date: 2022-11-10 22:37:45
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-11 14:45:32
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.寻找全排列的下一个数;

import java.util.Arrays;

/**
 * 寻找全排列的下一个数 - 字典序算法
 * 
 * 给出一个正整数，找出这个正整数所有数字全排列的`下一个数`
 * 
 * 说通俗点就是在一个整数所包含数字的全部组合中，找到一个大于且仅大于原 数的新整数
 * 
 * 让我们举几个例子：
 * 如果输入12345，则返回12354
 * 如果输入12354，则返回12435
 */
public class Solution {

    /**
     * 【思考】
     * 由固定几个数字组成的整数1 2 3 4 5，
     * 
     * 怎样排列最大？ - 逆序排列 54321
     * 怎样排列最小？ - 顺序排列 12345
     * 
     * 数字的顺序和逆序，是全排列中的两种极端情况
     * 
     * 3个步骤的时间复杂度都是：O(n)，所以总体的时间复杂度也是：O(n)
     */
    public static Integer getNextNumber(int number) {
        // 获取number是几位数
        int count = numberCount(number);
        System.out.println("是" + count + "位数");

        // 一位数，不用找
        if (count == 1) {
            return null;
        }

        // 把number的每一个数字放入array中
        int[] numberArray = number2IntArray(number, count);

        // 1、找到逆序的数组的下标
        int reverseIndex = findReverseIndex(numberArray);
        if (reverseIndex == -1) {
            return null;
        }

        // 2、让逆序数的前一个数和逆序区域中的最小数交换位置
        exchangeHead(numberArray, reverseIndex);

        // 3、让逆序区域的数排序
        sortReverseArea(numberArray, reverseIndex);

        // 4、返回结果
        return getNumberFromArray(numberArray);
    }

    /**
     * 让逆序区域的数字转为顺序
     */
    private static void sortReverseArea(int[] numberArray, int reverseIndex) {
        for (int i = reverseIndex, j = numberArray.length - 1; i < j; i++, j--) {
            int temp = numberArray[i];
            numberArray[i] = numberArray[j];
            numberArray[j] = temp;
        }
    }

    /**
     * 让逆序数的前一个数和逆序区域中的最小数交换位置
     * 
     * @param numberArray  数字数组
     * @param reverseIndex 逆序区域首个数字的下标
     */
    private static void exchangeHead(int[] numberArray, int reverseIndex) {
        // 找到逆序区域中的最小值和最小值的下标
        int min = numberArray[reverseIndex];
        int minIndex = reverseIndex;
        for (int i = reverseIndex; i < numberArray.length; i++) {
            if (numberArray[i] < min) {
                min = numberArray[i];
                minIndex = i;
            }
        }

        // 交换位置
        int temp = numberArray[reverseIndex - 1];
        numberArray[reverseIndex - 1] = numberArray[minIndex];
        numberArray[minIndex] = temp;
    }

    /**
     * `从后向前`查看逆序区域的下标
     * 
     * 也就是一个数比前一个数字大时，说明这个数是逆序区域的开始
     * 比如12354
     * 1、4比5小，顺序
     * 2、5比3大，逆序，返回5的下标
     * 
     * 123[54]
     */
    private static int findReverseIndex(int[] numberArray) {
        for (int i = numberArray.length - 1; i > 0; i--) {
            if (numberArray[i] > numberArray[i - 1]) { // 找到逆序区域的前一位
                return i;
            }
        }

        return -1;
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

    /**
     * 把整型数组的数字拼成一个大数
     */
    private static int getNumberFromArray(int[] numberArray) {
        System.out.println(Arrays.toString(numberArray));
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

    public static void main(String[] args) {
        // int a = 12345;
        int a = 54321;
        System.out.println("全排列的下一个数：" + getNextNumber(a));
    }
}
