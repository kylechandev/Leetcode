package 小灰.面试中的算法.寻找缺失的整数;

import java.util.Arrays;

/**
 * 寻找缺失的整数 - 扩展
 */
public class Solution2 {

    /**
     * 扩展：
     * 一个无序数组里有若干个正整数，范围是1～100，其中99个整数都出现了偶数次，只有1个整数出现了奇数次，如何找到这个出现奇数次的整数？
     * 
     * 解法：
     * 依次进行异或操作，相同的数最终都会被抵消，变为0，只有剩下的那个奇数会留下来
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int findNumber(int[] array) {
        if (array.length == 0) {
            throw new IllegalStateException();
        }

        int oxr = 0;
        for (int i = 0; i < array.length; i++) {
            oxr ^= array[i];
        }

        return oxr;
    }

    /**
     * 扩展：
     * 一个无序数组里有若干个正整数，范围是1～100，其中90个整数都出现了偶数次，只有2个整数出现了奇数次，如何找到这2个出现奇数次的整数？
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int[] findNumber2(int[] array) {
        int[] result = new int[2];

        int oxr = 0;
        for (int i = 0; i < array.length; i++) {
            oxr ^= array[i];
        }

        if (oxr == 0) {
            return null;
        }

        // 找到二进制位为1的数
        int separator = 1;
        while ((oxr & separator) == 0) {
            separator <<= 1;
        }

        for (int i = 0; i < array.length; i++) {
            // 分治法
            // 把两个不同的奇数分到不同的两组
            if ((array[i] & separator) == 0) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("出现奇数次的数：" + findNumber(new int[] { 4, 1, 2, 2, 5, 1, 4 }));
        System.out.println("出现奇数次的数：" + Arrays.toString(findNumber2(new int[] { 4, 1, 2, 2, 5, 1, 4, 3 })));
    }
}
