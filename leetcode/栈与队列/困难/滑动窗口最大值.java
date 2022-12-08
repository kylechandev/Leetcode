/*
 * @Author: kaic
 * @Date: 2022-12-07 20:47:49
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-08 15:27:38
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.困难;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值
 * 
 * 困难
 * 
 * 
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回 滑动窗口中的最大值 。
 * 
 * 
 * 示例：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 
 * 
 * https://leetcode.cn/problems/sliding-window-maximum/
 */
public class 滑动窗口最大值 {

    /**
     * 暴力（超时）
     * 
     * 时间复杂度：O(nk)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int size = nums.length;
        int[] result = new int[size - k + 1];

        int left = 0;
        int right = k - 1;

        while (right < size) {
            result[left] = getSubmax(nums, left++, right++);
        }

        return result;
    }

    private static int getSubmax(int[] nums, int from, int to) {
        int max = nums[from];
        for (int i = from; i <= to; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

    /**
     * 单调队列
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        int num = 0;

        MyMonotonicallyQueue myQueue = new MyMonotonicallyQueue();
        for (int i = 0; i < k; i++) {
            myQueue.offer(nums[i]);
        }
        result[num++] = myQueue.maxValue();

        for (int i = k; i < nums.length; i++) { // 确保队列里维护的始终是滑动窗口中的k个数
            myQueue.poll(nums[i - k]);
            myQueue.offer(nums[i]);
            result[num++] = myQueue.maxValue();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        System.out.println(Arrays.toString(new 滑动窗口最大值().maxSlidingWindow2(nums, k)));
    }
}

class MyMonotonicallyQueue {

    private Deque<Integer> queue = new ArrayDeque<>();

    public void poll(int value) {
        // 如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
        if (!queue.isEmpty() && value == maxValue()) {
            // value == queue.peek() 也就是等于当前队列中的最大值时 才真正进行pop的操作，
            // 因为`小值`在做添加一个大值后，都被自动移除了（offer函数的实现）
            queue.poll();
        }
    }

    public void offer(int value) {
        while (!queue.isEmpty() && value > queue.getLast()) {
            queue.removeLast(); // 注意了，从后往前比较，一个一个删除，所以要使用removeLast
        }
        queue.offer(value);
    }

    public int maxValue() {
        return queue.peek();
    }
}
