/*
 * @Author: kaic
 * @Date: 2022-12-03 12:20:37
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-03 12:40:18
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.哈希表.简单;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 
 * 简单
 * 
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 
 * 「快乐数」 定义为：
 * 
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * 
 * 
 * https://leetcode.cn/problems/happy-number/
 */
public class 快乐数 {

    /**
     * 求每位数字的平方和
     */
    private static int sumGeWeiPow(int n) {
        if (n == 0) {
            return 0;
        }

        return (int) Math.pow(n % 10, 2) + sumGeWeiPow(n / 10);
    }

    public static boolean isHappy(int n) {
        // 1 <= n <= 231 - 1

        Set<Integer> set = new HashSet<>();
        int num = n;
        while (true) {
            int sum = sumGeWeiPow(num);
            if (sum == 1) {
                return true;
            } else if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                num = sum;
            }
        }
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
    }
}
