package leetcode.哈希表.简单;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 
 * 简单
 * 
 * 
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。 *
 * 
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 */
public class 两个数组的交集 {

    /**
     * 两个set
     * 
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(m+n)
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> set = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }

        Set<Integer> resultSet = new HashSet<>();
        for (int n : nums2) {
            if (set.contains(n)) {
                resultSet.add(n);
            }
        }

        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 排序 + 双指针
     * 
     * 时间复杂度：O(mlogm+nlogn)
     * 空间复杂度：O(logm+logn)
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length;
        int length2 = nums2.length;

        int index = 0;
        int index1 = 0;
        int index2 = 0;

        int[] result = new int[length1 + length2];

        while (index1 < length1 && index2 < length2) {
            int n1 = nums1[index1];
            int n2 = nums2[index2];

            if (n1 == n2) {
                if (index == 0 || result[index - 1 < 0 ? 0 : index - 1] != n1) {
                    result[index] = n1;

                    index++;
                }
                index1++;
                index2++;
            } else if (n1 > n2) {
                index2++;
            } else if (n1 < n2) {
                index1++;
            }
        }

        return Arrays.copyOfRange(result, 0, index);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 2, 1 };
        int[] nums2 = new int[] { 2, 2 };
        System.out.println(Arrays.toString(intersection2(nums1, nums2)));
    }
}
