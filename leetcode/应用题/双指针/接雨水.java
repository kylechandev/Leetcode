/*
 * @Author: kaic
 * @Date: 2022-12-08 16:40:34
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-08 17:55:09
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
     * 双指针（按列求，行固定为1）
     */
    public static int trap(int[] height) {
        int sum = 0;

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

    public static void main(String[] args) {
        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap(height));
    }
}
