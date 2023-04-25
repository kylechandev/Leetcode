/*
 * @Author: kaic
 * @Date: 2023-04-25 13:12:02
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-25 13:36:04
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数学.中等;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合 - 类似于求[3,2,1]中所有子集的搜索树
 * 
 * 中等
 * 
 * 
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 
 * 
 * 示例：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 
 * 
 * https://leetcode.cn/problems/combinations/description/
 * 
 * 
 * 讲解：
 * https://www.bilibili.com/video/BV1xG4y1F7nC
 */
public class 组合 {

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 从大往小开始枚举
        dfs(n, k);

        return ans;
    }

    private void dfs(int i, int k) {
        // 剪枝
        int d = k - path.size(); // 还需要的个数d = 目标个数k - 已确认的个数`path.size()`
        if (i < d) { // 此时没法选够 k 个数了（i表示枚举开始位置，比如i=3，那么此时还有1,2,3三个数可以选择）
            // 以 n=4, k=2 为举例，
            // 当枚举到 i=1 的时候，此时只剩下 1 这一个数了，很显然没法满足要求的 k 个数，所有直接 return 剪枝
            return;
        }

        // 边界条件
        // 长度到达要求的 k，记录答案
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 继续枚举
        for (int j = i; j > 0; j--) {
            path.add(j);
            dfs(j - 1, k);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        // 1,2,...,n
        int n = 4, k = 2;
        System.out.println(new 组合().combine(n, k));
    }
}
