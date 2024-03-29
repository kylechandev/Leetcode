/*
 * @Author: kaic
 * @Date: 2022-10-29 13:32:24
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 23:12:41
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.应用题.双指针;

/**
 * 11. 盛最多水的容器
 * 
 * 中等
 * 
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 
 * 【注意】：
 * 题目给出n条线，但我们一共只能使用2条线，其余的线是不能用的，leetcode中的示例图把n条直接全画上去了，实际使用的线用红色标注了，可能会产生解题误解
 * 
 * https://leetcode.cn/problems/container-with-most-water/
 * 
 * Created by kaic on 2022/10/29
 */
public class 盛最多水的容器 {

    /**
     * 双指针（首尾）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int maxArea(int[] height) {
        // height数组的值是每条垂线的高度

        // 可容纳水的高度由两板中的 短板 决定（因为要求容纳最多的水，两条线围成的容器的容量最终有短线决定，因为高线高出来的部分也溢出了）
        // 因此可得如下 面积公式 ：
        // S(l,r)=min(h[l],h[r])×(r−l)

        // n条垂线
        int n = height.length;

        int l = 0;
        int r = n - 1;

        int max = 0;

        while (l < r) {
            int w = r - l;
            int h = Math.min(height[l], height[r]);
            int sum = w * h;
            max = Math.max(max, sum);

            // 小的前进
            // 因为移动短板，下次的短板可能变大（Math.min(height[l], height[r])），下个水槽面积可能增大
            // 因为移动长板，下次的短板可能不变或变小（Math.min(height[l], height[r])），下个水槽面积一定减小
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int maxArea = maxArea(height);
        System.out.println("max area: " + maxArea);
    }
}
