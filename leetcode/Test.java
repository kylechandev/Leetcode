/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-13 21:04:45
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode;

/**
 * 临时算法
 */
public class Test {

    public static int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int max = 0;

        while (l < r) {
            int w = r - l;
            int h = Math.min(height[l], height[r]);
            max = Math.max(max, w * h);

            // 小的一边移动
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(maxArea(heights));
    }
}
