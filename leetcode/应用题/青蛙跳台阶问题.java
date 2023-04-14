package leetcode.应用题;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题 / 70. 爬楼梯
 * 
 * 简单
 * 
 * 
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 */
public class 青蛙跳台阶问题 {

    public static int climbStairs(int n) {

        return 1;
    }

    public static void main(String[] args) {
        int n = 10;
        int count = climbStairs(n);

        System.out.println(count);
    }
}
