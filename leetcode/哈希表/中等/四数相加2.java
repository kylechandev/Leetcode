package leetcode.哈希表.中等;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加II
 * 
 * 中等
 * 
 * 
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 
 * 
 * https://leetcode.cn/problems/4sum-ii/
 */
public class 四数相加2 {

    /**
     * 暴力（超时）
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                for (int k = 0; k < nums3.length; k++) {
                    for (int p = 0; p < nums4.length; p++) {
                        if (nums1[i] + nums2[j] + nums3[k] + nums4[p] == 0) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    /**
     * ff`
     */
    public static int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;

        int tempSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // 先计算nums1和nums2数组两两元素之和，并分别保存他们出现的次数
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                tempSum = nums1[i] + nums2[j];
                map.put(tempSum, map.getOrDefault(tempSum, 0) + 1);
            }
        }

        // 再计算nums3和nums4数组两两元素之和，如果存在和nums1和nums2数组元素之和相反数，说明找到和为0出现的次数
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                tempSum = nums3[i] + nums4[j];

                if (map.containsKey(-tempSum)) {
                    count += map.get(-tempSum);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2 };
        int[] nums2 = new int[] { -2, -1 };
        int[] nums3 = new int[] { -1, 2 };
        int[] nums4 = new int[] { 0, 2 };

        System.out.println(fourSumCount2(nums1, nums2, nums3, nums4));
    }
}
