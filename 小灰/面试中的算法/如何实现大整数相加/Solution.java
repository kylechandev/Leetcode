/*
 * @Author: kaic
 * @Date: 2022-11-11 17:00:25
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-11 17:31:53
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.如何实现大整数相加;

import java.util.Arrays;

/**
 * 如何实现大整数相加
 * 
 * 给出两个很大的整数，要求实现程序求出两个整数之和
 */
public class Solution {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static String getTwoBigNumSum(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int maxLength = aLength > bLength ? aLength : bLength;

        int arrayLength = maxLength + 1;

        // 1、创建两个数组，长度为最大数字的位数+1（目的是给最高位进位预留），并且整数倒序存放（目的是符合数组从左到右访问的习惯）
        // ！创建数组只是为了更加直观！
        // ！也可以不创建这两个数组，直接使用String（先补齐0到首位），然后使用charAt获取到对应位置的数字进行运算，进位使用额外的变量来保存！
        int[] aArray = getArrayFromString(a, arrayLength);
        int[] bArray = getArrayFromString(b, arrayLength);

        System.out.println(Arrays.toString(aArray));
        System.out.println(Arrays.toString(bArray));

        // 2、创建结果数组
        int[] result = new int[arrayLength];

        // 3、开始按位相加（进位存储到result数组的下一位中）
        for (int i = 0; i < arrayLength; i++) {
            int n1 = aArray[i];
            int n2 = bArray[i];

            int sum = n1 + n2;
            int jinwei = result[i];
            if (jinwei != 0) {
                // 处理进位
                sum += 1;
            }
            if (sum < 10) {
                result[i] = sum;
            } else {
                // 有进位
                // 获取个数位
                result[i] = sum % 10;
                // 进位存储到下一位中
                result[i + 1] = 1;
            }
        }

        // 4、返回结果
        StringBuilder sb = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            sb.append(result[i]);
        }

        // 移除最高位多余的进位
        if (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    /**
     * 把String转化成int数组
     */
    private static int[] getArrayFromString(String string, int length) {
        int[] array = new int[length];

        for (int i = string.length() - 1; i >= 0; i--) {
            array[string.length() - i - 1] = string.charAt(i) - 48;
        }

        return array;
    }

    public static void main(String[] args) {
        String a = "426709752318";
        String b = "95481253129";

        System.out.println(a + "+" + b + "=" + getTwoBigNumSum(a, b));
    }
}
