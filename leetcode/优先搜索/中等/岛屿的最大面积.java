/*
 * @Author: kaic
 * @Date: 2023-04-27 10:18:23
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 10:25:20
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.中等;

/**
 * 695. 岛屿的最大面积
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/max-area-of-island/
 */
public class 岛屿的最大面积 {

    public int maxAreaOfIsland(int[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;

        int area = 0;

        // 开始挨个遍历
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == 1) { // 如果是陆地，执行dfs标记周围的陆地（形成整个岛屿）
                    area = Math.max(area, dfs(grid, i, j));
                }
            }
        }

        return area;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (!isInArea(grid, r, c)) {
            return 0;
        }

        // 不是陆地
        if (grid[r][c] != 1) {
            return 0;
        }

        // 标记走过
        grid[r][c] = 2;

        return dfs(grid, r + 1, c) +
                dfs(grid, r - 1, c) +
                dfs(grid, r, c + 1) +
                dfs(grid, r, c - 1) +
                1; // 每遍历到一个陆地格子，面积就+1
    }

    private boolean isInArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length &&
                c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        岛屿的最大面积 demo = new 岛屿的最大面积();

        int[][] grid = new int[][] {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
        };

        System.out.println(demo.maxAreaOfIsland(grid));
    }
}
