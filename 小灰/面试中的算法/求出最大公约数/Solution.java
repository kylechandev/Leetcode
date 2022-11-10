/*
 * @Author: kaic
 * @Date: 2022-11-10 17:11:22
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 18:05:51
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.求出最大公约数;

/**
 * 求出最大公约数
 */
public class Solution {

    /**
     * 时间复杂度：O(n)
     */
    public static int getGreatestCommonDivisor1(int a, int b) {

        int bigger = a > b ? a : b;
        int smaller = a > b ? b : a;

        if (bigger % smaller == 0) {
            return smaller;
        }

        // 1、
        // int max = -1;
        // for (int i = 1; i <= smaller; i++) {
        // if (a % i == 0 && b % i == 0) {
        // // 要求能同时被a和b整除
        // // ok
        // if (max < i) {
        // max = i;
        // }
        // }
        // }
        // return max;

        // 2、
        // 既然要求求出最大公约数，那不如从后往前遍历，找到的第一个数就是符合要求的数
        for (int i = smaller; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 时间复杂度：O(n)
     */
    public static int getGreatestCommonDivisor2(int a, int b) {

        // 3、
        // 辗转相除法（又名：欧几里得算法）
        // 定理：两个正整数a和b（a>b），它们的最大公约数等于`a除以b的余数c`和`b(小值)`之间的最大公约数
        // 算法：递归

        int max = a > b ? a : b;
        int min = a > b ? b : a;

        if (max % min == 0) {
            // 递归结束条件
            return min;
        }

        return getGreatestCommonDivisor2(max % min, min);
    }

    public static int gcd(int a, int b) {
        // 算法同 getGreatestCommonDivisor2
        // 只是这里直接假设a为小值，b为大值，然后进行递归

        if (a == 0) {
            // 递归结束条件：假设的`小数==0`
            return b;
        }

        // 如果首次传入的a大b小，第一次递归就相当于交换a和b的值
        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        int a = 112;
        int b = 18;
        System.out.println(a + "和" + b + "的最大公约数是：" + getGreatestCommonDivisor1(a, b));
        System.out.println(a + "和" + b + "的最大公约数是：" + getGreatestCommonDivisor2(a, b));
        System.out.println(a + "和" + b + "的最大公约数是：" + gcd(a, b));

        // 5原码 0000 0101
        // 5补码 0000 0101 - 要拿补码进行计算
        // ~运算 1111 1010 - 注意包括符号位要全部取反
        // '补码 1000 0110 - 得到最后结果（计算机中存储的补码）
        System.out.println("5取反：" + ~5); // -6
    }
}
