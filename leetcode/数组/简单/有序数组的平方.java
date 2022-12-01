package leetcode.数组.简单;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * 
 * 简单
 * 
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 
 * 
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class 有序数组的平方 {

    /**
     * 暴力
     * 
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) Math.pow(nums[i], 2);
        }

        Arrays.sort(nums);

        return nums;
    }

    /**
     * 双指针法
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] sortedSquares2(int[] nums) {
        int[] result = new int[nums.length];
        int k = result.length - 1;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // 由于存在负数，所以平方之后可能成为最大数，而这个最大数要么在最左侧要么在最右侧，
            // 所以使用双指针法，分别从左右两边取最大值向result数组的末尾添加值
            int left2 = nums[left] * nums[left];
            int right2 = nums[right] * nums[right];
            if (left2 > right2) {
                result[k--] = left2;
                left++;
            } else {
                result[k--] = right2;
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -4, -1, 0, 3, 10 };
        System.out.println(Arrays.toString(new 有序数组的平方().sortedSquares2(nums)));
    }
}
