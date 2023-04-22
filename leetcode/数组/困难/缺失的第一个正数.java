/*
 * @Author: kaic
 * @Date: 2022-11-24 20:42:58
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-18 23:45:27
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数组.困难;

import java.util.HashSet;
import java.util.Set;

/**
 * 41. 缺失的第一个正数
 * 
 * 困难
 * 
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 
 * 要求空间复杂度为：O(1)
 * 
 * 
 * 示例1:
 * 输入：nums = [1,2,0]
 * 输出：3
 * 
 * 示例2:
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 
 * 示例3:
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 
 * https://leetcode.cn/problems/first-missing-positive/description/
 */
public class 缺失的第一个正数 {

    /**
     * 借助Set
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        // 将所有的数组元素添加到set中
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // 由于是找最小的正整数，那么最小的正整数肯定是1，且最大的正整数必然是数组的长度
        int length = nums.length;

        // 从最小正整数1开始遍历，最大的正整数只可能是nums.length
        for (int i = 1; i <= length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        // 没有找到，说明原数组是顺序的，原数组序列中没有缺失的数，所以返回数组长度+1
        return length + 1;
    }

    /**
     * 原地hash排序
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int firstMissingPositive2(int[] nums) {
        int length = nums.length;

        // 进行原地hash排序数组
        for (int i = 0; i < length; i++) {
            int num = nums[i];

            // num >= 1 && num <= length 确保数字在有效范围内
            // nums[num-1] != num 确保要和交换到的那个位置的数不同（防止有重复数导致死循环）
            // while 因为每个索引位置上的数字可能并不是一次交换就可以了，所以需要使用while一直进行交换
            // 例如：[4,1,2,3]，第一次for循环，当前在索引为0的位置
            // 第一次交换：数字4要交换放在索引为4-1=3的位置上：[3,1,2,4]，此时索引0位置上的3还是不符合要求，while继续
            // 第二次交换：数字3要交换放在所因为3-1=2的位置上：[2,1,3,4]，此时索引0位置上的2还是不符合要求，while继续
            // 第三次交换：数字2要交换放在所因为2-1=1的位置上：[1,2,3,4]，此时索引0位置上的1已经符合要求了，
            // nums[num - 1] == num（1==1），while退出
            // 进入第二次for循环，当前在索引为1的位置，可以看出位置已经符合要求了，
            // while判断条件`nums[num - 1] != num`生效，while循环不会再执行了（那么这个条件的防止死循环的目的就达到了）

            while (num >= 1 && num <= length && nums[num - 1] != num) {
                // 把数字交换到他应该在的位置，比如说数字num=3，他应该放在下标为2的位置上
                // 而下标2的获取方式就是：num-1，下标2所在位置的值就是：nums[num - 1]
                // 开始交换（让nums[i]和nums[num-1]交换）
                int temp = nums[num - 1];
                nums[num - 1] = num;
                nums[i] = temp;

                // 更新缓存的num值
                num = temp;
            }
        }

        // 现在nums数组已经排序完成
        // 找到未出现的最小正整数
        // 由于是找最小的正整数，那么最小的正整数肯定是1，且最大的正整数必然是数组的长度，即 [1 ~ nums.length]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 没有找到的话就返回length+1
        return length + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 3, 4, -1, 1 };
        System.out.println(firstMissingPositive2(nums));
    }
}
