/*
 * @Author: kaic
 * @Date: 2022-12-08 16:40:34
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-08 22:59:54
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.应用题.双指针;

import java.util.Stack;

/**
 * 42. 接雨水
 * 
 * 困难
 * 
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class 接雨水 {

    /**
     * 方法一：按行求，列固定为1
     * 
     * 时间复杂度：O(m*n)
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
     * 方法二：按列求，行固定为1
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

            // 当前格子的高度
            int h = height[i];

            // 计算左侧最大值
            int leftMaxHeight = h;
            for (int l = i - 1; l >= 0; l--) {
                int lh = height[l];
                if (lh > leftMaxHeight) {
                    leftMaxHeight = lh;
                }
            }
            // 计算右侧最大值
            int rightMaxHeight = h;
            for (int r = i + 1; r < height.length; r++) {
                int rh = height[r];
                if (rh > rightMaxHeight) {
                    rightMaxHeight = rh;
                }
            }

            // 统计
            int column = Math.min(leftMaxHeight, rightMaxHeight) - h;
            if (column >= 0) {
                sum += column;
            }
        }

        return sum;
    }

    /**
     * 方法三：动态规划 - 基于方法二按列求，只是这次我们在计算左右侧最大值时改为统一一次性计算了
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int trap3(int[] height) {
        int sum = 0;
        if (height.length == 0) {
            return sum;
        }

        // 1、统一计算左侧最大值和右侧最大值
        int[] maxLeft = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) { // 从左往右统计
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        int[] maxRight = new int[height.length];
        for (int i = height.length - 2; i >= 0; i--) { // 从右往左统计
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        // 2、统计
        for (int i = 1; i < maxRight.length - 1; i++) {
            int s = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (s > 0) {
                sum += s;
            }
        }

        return sum;
    }

    /**
     * 方法四：双指针 - 基于方法三动态规划优化，减少了空格消耗
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int trap4(int[] height) {
        int sum = 0;
        if (height.length == 0) {
            return sum;
        }

        // 左右最大值
        int maxLeft = 0;
        int maxRight = 0;

        // 添加左右指针
        int left = 1;
        int right = height.length - 2;

        // 遍历次数
        int count = height.length - 2;
        while (count > 0) {
            /*
             * height [ left - 1] 是可能成为 max_left 的变量，
             * 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
             * 
             * 只要保证 height [ left - 1 ] < height [ right + 1 ] ，那么 max_left 就一定小于 max_right。
             * 
             * 因为 max_left 是由 height [ left - 1] 更新过来的，
             * 而 height [ left - 1 ] 是小于 height [ right + 1] 的，
             * 而 height [ right + 1 ] 会更新 max_right，所以间接的得出 max_left 一定小于 max_right。
             */
            if (height[left - 1] < height[right + 1]) {
                // 左到右
                // 此时可以确定最小值在左侧，所以直接判断左侧的最小值就行了，不需要关心右侧
                maxLeft = Math.max(maxLeft, height[left - 1]);
                int s = maxLeft - height[left];
                if (s > 0) {
                    sum += s;
                }
                left++;
            } else {
                // 右到左
                // 此时可以确定最小值在右侧，所以直接判断右侧的最小值就行了，不需要关心左侧
                maxRight = Math.max(maxRight, height[right + 1]);
                int s = maxRight - height[right];
                if (s > 0) {
                    sum += s;
                }

                right--;
            }

            count--;
        }

        return sum;
    }

    /**
     * 方法五：单调栈（按行统计）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int trap5(int[] height) {
        int sum = 0;

        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            // 如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int target = height[stack.pop()]; // 取出要出栈的元素
                if (stack.empty()) { // 栈空就出去
                    break;
                }

                int distance = current - stack.peek() - 1; // 两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                System.out
                        .println("curent: " + current + ", stack.peek(): " + stack.peek() + ", distance: " + distance
                                + ", min-target: " + (min - target));
                sum += distance * (min - target);
            }
            stack.push(current); // 当前指向的墙入栈
            current++; // 指针后移
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap5(height));
    }
}
