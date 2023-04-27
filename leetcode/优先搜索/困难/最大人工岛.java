/*
 * @Author: kaic
 * @Date: 2023-04-27 10:33:59
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 13:15:49
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.困难;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 827. 最大人工岛/填海造陆问题
 * 
 * 困难
 * 
 * 「本地类似 岛屿的最大面积 ，在此基础上增加了可以人工建岛的能力，可以将一个 0 变成 1」
 * 
 * https://leetcode.cn/problems/making-a-large-island/
 */
public class 最大人工岛 {

    private static final int INITIAL_ISLAND_INDEX = 2;

    private int curIslandIndex = INITIAL_ISLAND_INDEX;
    private HashMap<Integer, Integer> map = new HashMap<>();

    public int largestIsland(int[][] grid) {
        int ans = 0;

        int nr = grid.length;
        int nc = grid[0].length;

        // 先一遍 DFS 陆地，得到所有岛屿以及它们的面积
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == 1) { // 找到陆地
                    int area = dfsIsland(grid, i, j);

                    // 记录岛屿索引 以及 它的面积
                    map.put(curIslandIndex, area);

                    curIslandIndex++;
                }
            }
        }

        System.out.println("岛屿信息：" + map);

        if (map.isEmpty()) {
            // 没有陆地，全是海洋，返回 1 个人工建岛
            return 1;
        }

        // 再一遍 DFS 海洋，找到 相邻两块陆地 的海洋
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == 0) { // 找到海洋
                    Set<Integer> islandIndexSet = dfsSea(grid, i, j);
                    // 更新最大岛屿面积，+1 是人工建岛
                    ans = Math.max(ans, calculateSumArea(islandIndexSet) + 1);
                }
            }
        }

        if (ans == 0) {
            // 没有海洋，全是岛屿
            return map.get(INITIAL_ISLAND_INDEX);
        } else {
            return ans;
        }
    }

    /**
     * 计算标记的 海洋 的相邻 N 个陆地的索引 对应的陆地的 面积 的总和
     */
    private int calculateSumArea(Set<Integer> islandIndexSet) {
        int sum = 0;

        for (Integer index : islandIndexSet) {
            sum += map.getOrDefault(index, 0);
        }

        return sum;
    }

    private int dfsIsland(int[][] grid, int r, int c) {
        if (!isInArea(grid, r, c)) { // 越界
            return 0;
        }

        if (grid[r][c] != 1) { // 是海洋或者已经访问过的陆地
            return 0;
        }

        // 标记已访问过的陆地的索引
        grid[r][c] = curIslandIndex;

        // 同时计算这个岛的面积
        return dfsIsland(grid, r + 1, c) +
                dfsIsland(grid, r - 1, c) +
                dfsIsland(grid, r, c + 1) +
                dfsIsland(grid, r, c - 1) +
                1; // 每遍历到一个陆地格子，面积就+1
    }

    /**
     * 找符合要求的海洋，即有两条边和陆地相连的
     */
    private Set<Integer> dfsSea(int[][] grid, int r, int c) {
        // 保存相邻陆地的索引
        Set<Integer> res = new HashSet<>();

        if (isInArea(grid, r + 1, c) && grid[r + 1][c] != 0) {
            res.add(grid[r + 1][c]);
        }
        if (isInArea(grid, r - 1, c) && grid[r - 1][c] != 0) {
            res.add(grid[r - 1][c]);
        }
        if (isInArea(grid, r, c + 1) && grid[r][c + 1] != 0) {
            res.add(grid[r][c + 1]);
        }
        if (isInArea(grid, r, c - 1) && grid[r][c - 1] != 0) {
            res.add(grid[r][c - 1]);
        }

        return res;
    }

    private boolean isInArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length &&
                c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        最大人工岛 demo = new 最大人工岛();

        int[][] grid = new int[][] {
                // { 1, 1 },
                // { 1, 0 }
                { 0, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 1 },
                { 0, 1, 0, 0, 1 },
        };

        System.out.println(demo.largestIsland(grid));
    }
}
