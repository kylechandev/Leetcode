package leetcode.数组;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class 删除有序数组中的重复项 {

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solutions/575549/shua-chuan-lc-jian-ji-shuang-zhi-zhen-ji-2eg8/
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int a = removeDuplicates(nums);
        System.out.println(a);
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, a)));
    }
}
