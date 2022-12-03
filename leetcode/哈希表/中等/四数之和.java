/*
 * @Author: kaic
 * @Date: 2022-12-03 14:23:44
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-03 15:22:40
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.哈希表.中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 
 * 中等
 * 
 * 
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a],
 * nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * 
 * 
 * https://leetcode.cn/problems/4sum/
 */
public class 四数之和 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // 先对nums排序
        Arrays.sort(nums);

        // 双指针
        int left = 0;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target && nums[i] >= 0) { // 例子：[-4, -3, -2, -1] target=-10，所以需要增加条件`nums[i] >= 0`
                // 对nums[i]去重
                // 由于数组已经排序好了，如果第一个数就大于0，那么后续肯定不会有数字和fix加起来等于0
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                // 第一个数去重，如果fix和前一个数字相同，那么后续结果就重复了
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    // 对nums[j]去重
                    continue;
                }

                // 定位双指针
                left = j + 1;
                right = nums.length - 1;

                while (left < right) {
                    int leftNum = nums[left];
                    int rightNum = nums[right];

                    long sum = (long) nums[i] + nums[j] + leftNum + rightNum;

                    if (sum > target) {
                        // 结果太大，让大数指针减小
                        right--;
                    } else if (sum < target) {
                        // 结果太小，让小数指针增大
                        left++;
                    } else {
                        // 找到结果集
                        result.add(Arrays.asList(nums[i], nums[j], leftNum, rightNum));

                        // 开始去重left和right
                        // 去重的目的是去重结果集的重复，例如原数组是：[0,-1,-1,-1,-1,-1,1,1,1,1,1] 的情况
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 可能有的疑问？
                        // 上面while去重的逻辑中，如果进入执行了的话，那么while结束后left或者right不就已经到了正确的位置上吗？那后面再执行自增不就多增加了吗？
                        // 答：没有，例如[0,1,1,2]，while执行后left的下标从原来的index=1变为index=2，所以还是需要再自增+1操作的

                        // 双指针收缩
                        // 因为需要满足 i != j != k 的条件，所以必须同时移动两个指针
                        left++;
                        right--;
                    }
                }
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 0, -1, 0, -2, 2 };
        int target = 0;

        System.out.println(fourSum(nums, target));
    }
}
