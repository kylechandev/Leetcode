/*
 * @Author: kaic
 * @Date: 2022-12-08 16:40:34
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-08 18:15:48
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.应用题.双指针;

/**
 * 42. 接雨水
 * 
 * 困难
 * 
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class 接雨水 {

    /**
     * 按行求，列固定为1
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static int trap1(int[] height) {
        int sum = 0;
        if (height.length == 0) {
            return sum;
        }

        // 1、计算最高行数
        int maxLine = height[0];
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxLine) {
                maxLine = height[i];
            }
        }

        // 2、开始计算每一行的宽
        for (int lineHeight = 1; lineHeight <= maxLine; lineHeight++) { // 遍历行数

            int tempSum = 0; // 临时统计每一行的每一块格子的sum
            boolean start = false; // 标记这一行是否开始统计（当第一次遇到大于等于行数的格子后开始统计）

            for (int j = 0; j < height.length; j++) { // 遍历每一行的每一个格子
                if (start && height[j] < lineHeight) {
                    tempSum++;
                }

                if (height[j] >= lineHeight) {
                    sum += tempSum;
                    tempSum = 0;
                    start = true;
                }
            }
        }

        return sum;
    }

    /**
     * 按列求，行固定为1
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static int trap2(int[] height) {
        int sum = 0;
        if (height.length == 0) {
            return sum;
        }

        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) {
                continue;
            }

            int h = height[i];

            int leftMaxHeight = h;
            int rightMaxHeight = h;

            for (int l = i - 1; l >= 0; l--) {
                int lh = height[l];
                if (lh > leftMaxHeight) {
                    leftMaxHeight = lh;
                }
            }
            for (int r = i + 1; r < height.length; r++) {
                int rh = height[r];
                if (rh > rightMaxHeight) {
                    rightMaxHeight = rh;
                }
            }

            int column = Math.min(leftMaxHeight, rightMaxHeight) - h;
            if (column >= 0) {
                sum += column;
            }
        }

        return sum;
    }

    /**
     * 动态规划
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static int trap3(int[] height) {
        int sum = 0;

        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap1(height));
    }
}
