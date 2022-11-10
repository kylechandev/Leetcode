/*
 * @Author: kaic
 * @Date: 2022-11-10 18:08:39
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 18:24:59
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.判断一个数是否为2的整数次幂;

/**
 * 判断一个数是否为2的整数次幂
 */
public class Solution {

    /**
     * 时间复杂度：O(logn)
     */
    public static boolean isPowerOf2(int num) {
        if (num % 2 != 0) {
            return false;
        }

        int temp = 1;
        while (temp < num) {
            // temp *= 2;
            temp = temp << 1; // 提高性能：使用移位的方式
        }

        return temp == num;
    }

    public static boolean isPowerOf2V2(int num) {
        // 通过二进制`与`的方式
        // 2的整数次幂的二进制形式都如同：100...000
        // 它`-1`后的数二进制形式都如同：011...111
        // 所以让他们进行`与`操作后，结果必定是0
        // 这样也可以判断是否是2的整数次幂
        return (num & num - 1) == 0;
    }

    public static void main(String[] args) {
        int a = 16;

        System.out.println(a + "是否为2的整数次幂? " + isPowerOf2(a));
        System.out.println(a + "是否为2的整数次幂? " + isPowerOf2V2(a));
    }
}
