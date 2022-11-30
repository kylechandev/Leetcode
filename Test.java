/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-30 16:37:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

import java.util.Arrays;

/**
 * 临时算法
 */
public class Test {

    /**
     * 每轮排序从后往前确定排序完成的值
     */
    public static void bubbleSort(int[] array) {
        boolean flag = false;

        int border = array.length - 1;

        for (int i = 0; i < array.length - 1; i++) {
            flag = false;

            int lastChangeBorder = -1;

            for (int j = 0; j < border; j++) { // array.length - i - 1
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    flag = true;
                    lastChangeBorder = j;
                }
            }

            if (!flag) {
                break;
            }

            border = lastChangeBorder;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        bubbleSort(array);
        System.out.println("排序数组：\n" + Arrays.toString(array));
    }
}
