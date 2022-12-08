/*
 * @Author: kaic
 * @Date: 2022-12-08 15:30:47
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-08 15:57:24
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.中等;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 
 * 中等
 * 
 * 
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * *
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class 前K个高频元素 {

    public static int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        if (nums.length == 0) {
            return ans;
        }

        // 1、统计每个数字的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 2、对出现频率排序
        // a negative integer, zero, or a positive integer as the
        // first argument is less than, equal to, or greater than the second.
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.offer(new int[] { entry.getKey(), entry.getValue() });
            } else {
                if (entry.getValue() > pq.peek()[1]) {
                    pq.poll();
                    pq.offer(new int[] { entry.getKey(), entry.getValue() });
                }
            }
        }

        // 3、找出前k个高频元素
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
