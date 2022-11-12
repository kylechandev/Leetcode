package 小灰.面试中的算法.求解金矿问题;

import java.util.Arrays;

/**
 * 求解金矿问题 - 动态规划问题（和著名的背包问题类似）
 * 
 * 很久很久以前，有一位国王拥有5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人人数也不同。
 * 
 * 例如：
 * 有的金矿储量是200kg黄金，需要3个工人来挖掘；
 * 有的金矿储量是300kg黄金，需要4个工人来挖掘；
 * 有的金矿储量是350kg黄金，需要3个工人来挖掘；
 * 有的金矿储量是400kg黄金，需要5个工人来挖掘；
 * 有的金矿储量是500kg黄金，需要5个工人来挖掘；
 * 
 * 如果参与挖矿的工人的总数是10。
 * 每座金矿要么全挖，要么不挖，不能派出一半人挖取一半的金矿。
 * 
 * 要求用程序求出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
 */
public class Solution {

    /**
     * 动态规划 - 递归解法
     * 
     * 时间复杂度：O(2^n)
     * 
     * 比较低效，因为递归做了许多重复的计算
     * 
     * @param n 金矿数
     * @param w 工人数
     * @param g 每个金矿的含金量
     * @param p 每个金矿的开采人数
     */
    public static int getBestGoldMining1(int n, int w, int[] g, int[] p) {

        // 结束条件
        if (n == 0 || w == 0) {
            // 当 金矿数不够 或者 工人不够 时，结束
            return 0;
        }

        // 当工人不够挖矿时
        // 条件：n >= 1 && w < p[n - 1]
        // 条件：矿够 && 人不够挖这个矿
        if (n >= 1 && w < p[n - 1]) {
            // w 当前剩余的工人数
            // p[n-1] 最后一个矿开采所需的工人数

            // 解决：找下一个矿开始开采
            return getBestGoldMining1(n - 1, w, g, p); // n - 1: 下一个矿
        }

        // 当工人够挖当前矿时，可以选择挖或者不挖
        // 条件是：n >= 1 && w >= p[n-1]
        // 条件是：矿够 && 人够
        // 由于上面已经判断过`结束条件`和`不够挖的情况了`，所以这里肯定是够挖的了，可以不用再判断条件了

        // 不挖
        int buwa = getBestGoldMining1(n - 1, w, g, p);
        // 挖
        // n - 1 开始下个矿
        // w - p[n - 1] 挖完后剩下的可用工人
        // g[n - 1] 挖的那个矿的含金量
        int wa = getBestGoldMining1(n - 1, w - p[n - 1], g, p) + g[n - 1];

        // 返回最大采矿量
        return wa > buwa ? wa : buwa;
    }

    /**
     * 自底向上 - 动态规划
     * 
     * 时间复杂度：O(wn)
     * 空间复杂度：O(wn)
     * 
     * @param n 金矿数
     * @param w 工人数
     * @param g 每个金矿的含金量
     * @param p 每个金矿的开采人数
     */
    public static int getBestGoldMining2(int n, int w, int[] g, int[] p) {
        // 长度+1
        // 在最外层填充0数据，方便后面实际数据的计算
        int[][] table = new int[n + 1][w + 1];

        /*
         * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0, 400, 400, 400, 400, 400, 400]
         * [0, 0, 0, 0, 0, 500, 500, 500, 500, 500, 900]
         * [0, 0, 0, 200, 200, 500, 500, 500, 700, 700, 900]
         * [0, 0, 0, 200, 300, 500, 500, 500, 700, 800, 900]
         * [0, 0, 0, 350, 350, 500, 550, 650, 850, 850, 900]
         */

        // 从1开始遍历，最外层的0只是用来辅助计算的
        for (int i = 1; i <= n; i++) { // 遍历金矿数
            for (int j = 1; j <= w; j++) { // 遍历工人数
                if (j < p[i - 1]) {
                    // 人数不够挖
                    table[i][j] = table[i - 1][j];
                } else {
                    // 人数够挖
                    // 选择不挖
                    int buwa = table[i - 1][j]; // 还是j个工人，准备去挖剩下的i-1个矿
                    // 选择挖
                    // table[i - 1][j - p[i - 1]]
                    // [i-1] 剩下的i-1个矿
                    // p[i-1] 第i个矿所需的工人数（这里也用i-1是因为数组p的下标是从0开始的）
                    // j - p[i-1] 剩下的可用工人数
                    // g[i-1] 第i个矿的含金量（这里也用i-1是因为数组g的下标是从0开始的）
                    int wa = table[i - 1][j - p[i - 1]] + g[i - 1];

                    // 返回最大含金量
                    table[i][j] = wa > buwa ? wa : buwa;
                }
            }
        }

        // 打印
        for (int i = 0; i < table.length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }

        // 返回最后一个格子的数据
        return table[table.length - 1][table[table.length - 1].length - 1];
    }

    /**
     * 递归解法
     * 
     * 时间复杂度：O(2^n)
     * 
     * @param n 金矿数
     * @param w 工人数
     * @param g 每个金矿的含金量
     * @param p 每个金矿的开采人数
     */
    public static int getBestGoldMining3(int n, int w, int[] g, int[] p) {
        return 2;
    }

    public static void main(String[] args) {
        // 工人数
        int w = 10;
        // 挖矿所需的工人数
        int[] p = { 5, 5, 3, 4, 3 };
        // 每个矿的含金量
        int[] g = { 400, 500, 200, 300, 350 };
        System.out.println("最优收益1：" + getBestGoldMining1(g.length, w, g, p));
        System.out.println("最优收益2：" + getBestGoldMining2(g.length, w, g, p));
        System.out.println("最优收益3：" + getBestGoldMining3(g.length, w, g, p));
    }
}
