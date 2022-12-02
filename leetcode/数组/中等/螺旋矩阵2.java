/*
 * @Author: kaic
 * @Date: 2022-12-01 14:45:59
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-01 15:19:24
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.中等;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 
 * 中等
 * 
 * 
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 
 * 
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class 螺旋矩阵2 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int i = 0, j = 0;
        int offset = 1;
        int start = 0; // 每一圈的起始坐标(start,start)

        int count = 1;

        // 圈数
        int loop = n / 2;

        while (loop-- > 0) {
            // 上侧
            for (j = start; j < n - offset; j++) {
                result[start][j] = count++;
            }

            // 右侧
            for (i = start; i < n - offset; i++) {
                result[i][j] = count++;
            }

            // 下侧
            for (; j >= offset; j--) {
                result[i][j] = count++;
            }

            // 左侧
            for (; i >= offset; i--) {
                result[i][j] = count++;
            }

            offset++;
            start++;
        }

        if (n % 2 != 0) {
            result[start][start] = count;
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        for (int i = 0; i < n; i++) {
            int[][] result = new 螺旋矩阵2().generateMatrix(3);
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
