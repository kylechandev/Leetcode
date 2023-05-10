/*
 * @Author: kaic
 * @Date: 2022-12-01 14:45:59
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-10 22:55:12
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

        int l = 0, t = 0, r = n - 1, b = n - 1;
        int num = 1;
        int target = n * n;

        while (num <= target) {
            // left to right
            for (int i = l; i <= r; i++) {
                result[t][i] = num++;
            }
            t++;

            // top to bottom
            for (int i = t; i <= b; i++) {
                result[i][r] = num++;
            }
            r--;

            // right to left
            for (int i = r; i >= l; i--) {
                result[b][i] = num++;
            }
            b--;

            // bottom to top
            for (int i = b; i >= t; i--) {
                result[i][l] = num++;
            }
            l++;
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
