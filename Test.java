/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-06 10:34:44
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

/**
 * 临时算法
 */
public class Test {

    public static int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }

        return slowIndex;
    }

    public static int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            while (left < right && nums[left] != val) {
                left++;
            }
            while (left < right && nums[right] == val) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right--];
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(removeElement2(new int[] { 3, 2, 2, 3 }, 2));
    }
}
