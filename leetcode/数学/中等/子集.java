/*
 * @Author: kaic
 * @Date: 2023-04-25 10:12:57
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-25 10:42:09
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数学.中等;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 
 * 中等
 * 
 * 
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 
 * 示例：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 
 * https://leetcode.cn/problems/subsets/
 * 
 * 
 * 视频讲解：
 * https://www.bilibili.com/video/BV1mG4y1A7Gu
 */
public class 子集 {

    private final List<List<Integer>> ans = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return ans;
        }

        dfs2(0, nums);

        return ans;
    }

    /**
     * 站在 输入 的角度思考
     */
    private void dfs(int cur, int[] nums) {
        // 原序列的每个位置在答案序列中的状态有 被选中 和 不被选中 两种
        // 我们用 path 数组存放已经被选出的数字。

        // 边界条件
        if (cur == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 对于选当前位置
        path.add(nums[cur]); // 加入路径
        dfs(cur + 1, nums); // 继续下一个位置
        path.remove(path.size() - 1); // 回溯

        // 对于不选当前位置
        dfs(cur + 1, nums); // 继续下一个位置
    }

    /**
     * 站在 答案 的角度思考
     * 
     * 也就是每个数都选
     */
    private void dfs2(int cur, int[] nums) {
        // 正是因为 每个数都选 ，所以每次递归都需要记录答案（可以看成递归到的每个节点都是答案）
        ans.add(new ArrayList<>(path));

        if (cur == nums.length) {
            return;
        }

        // 开始枚举当前要填的数字
        for (int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums); // 这里是 i+1，不能是 cur+1
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        System.out.println(new 子集().subsets(nums));
    }
}
