/*
 * @Author: kaic
 * @Date: 2022-12-01 15:21:01
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-02 09:55:45
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.中等;

import java.util.Arrays;

/**
 * 54. 螺旋矩阵
 * 
 * 中等
 * 
 * 
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 
 * 
 * https://leetcode.cn/problems/spiral-matrix/
 */
public class 螺旋矩阵 {

    public int[] spiralOrder(int[][] matrix) {
        // if (matrix.length == 0) {
        // return new ArrayList<Integer>();
        // }
        if (matrix.length == 0) {
            return new int[] {};
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int count = row * column;

        // List<Integer> result = new ArrayList<>();

        int[] result = new int[count];
        int index = 0;

        int upperBound = 0;
        int lowerBound = row - 1;
        int leftBound = 0;
        int rightBound = column - 1;

        while (count-- > 0) {
            if (upperBound <= lowerBound) { // 确保边界移动后合法
                // 上侧从左到右
                for (int j = leftBound; j <= rightBound; j++) {
                    // result.add(matrix[upperBound][j]);
                    result[index++] = matrix[upperBound][j];
                }
                // 上边界下移
                upperBound++;
            }

            if (leftBound <= rightBound) { // 确保边界移动后合法
                // 右侧从上到下
                for (int i = upperBound; i <= lowerBound; i++) {
                    // result.add(matrix[i][rightBound]);
                    result[index++] = matrix[i][rightBound];
                }
                // 右边界左移
                rightBound--;
            }

            if (upperBound <= lowerBound) { // 确保边界移动后合法
                // 底部从右到左
                for (int j = rightBound; j >= leftBound; j--) {
                    // result.add(matrix[lowerBound][j]);
                    result[index++] = matrix[lowerBound][j];
                }
                // 下边界上移
                lowerBound--;
            }

            if (leftBound <= rightBound) { // 确保边界移动后合法
                // 左部从下到上
                for (int i = lowerBound; i >= upperBound; i--) {
                    // result.add(matrix[i][leftBound]);
                    result[index++] = matrix[i][leftBound];
                }
                // 左边界右移
                leftBound++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        // System.out.println(new 螺旋矩阵().spiralOrder(matrix));
        System.out.println(Arrays.toString(new 螺旋矩阵().spiralOrder(matrix)));
    }
}
