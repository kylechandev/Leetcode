package leetcode.哈希表.中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 
 * 中等
 * 
 * 
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j !=
 * k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 
 * 你返回所有和为 0 且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 
 * 【注意】
 * 需要 i != j != k
 * 同时多个 (nums[i], nums[j], nums[k]) 不同重复
 * 
 * https://leetcode.cn/problems/3sum/
 */
public class 三数之和 {

    /**
     * 双指针
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 先对nums排序
        Arrays.sort(nums);

        // 双指针
        int left = 0;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            int fix = nums[i]; // 第一个固定数

            if (fix > 0) {
                // 由于数组已经排序好了，如果第一个数就大于0，那么后续肯定不会有数字和fix加起来等于0
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                // 第一个数去重，如果fix和前一个数字相同，那么后续结果就重复了
                continue;
            }

            // 定位双指针
            left = i + 1;
            right = nums.length - 1;

            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];

                int sum = fix + leftNum + rightNum;

                if (sum > 0) {
                    // 结果太大，让大数指针减小
                    right--;
                } else if (sum < 0) {
                    // 结果太小，让小数指针增大
                    left++;
                } else {
                    // 找到结果集
                    result.add(Arrays.asList(fix, leftNum, rightNum));

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

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };

        System.out.println(threeSum(nums));
    }
}
