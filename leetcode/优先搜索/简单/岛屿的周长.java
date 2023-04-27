/*
 * @Author: kaic
 * @Date: 2023-04-27 09:52:45
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 10:16:37
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.简单;

/**
 * 463. 岛屿的周长 - 「本题是很好的理解DFS遍历过程的例题」
 * 
 * 简单
 * 
 * 
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100。计算这个岛屿的周长。
 * 
 * 
 * https://leetcode.cn/problems/island-perimeter/
 */
public class 岛屿的周长 {

    public int islandPerimeter(int[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;

        // 开始挨个遍历
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == 1) { // 如果是陆地，执行dfs标记周围的陆地（形成整个岛屿）
                    return dfs(grid, i, j);
                }
            }
        }

        return -1;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (!isInArea(grid, r, c)) {
            return 1; // grid临界边
        }

        if (grid[r][c] == 0) {
            return 1; // 海洋临界边
        }

        // 不是岛屿
        if (grid[r][c] != 1) {
            return 0;
        }

        // 标记走过
        grid[r][c] = 2;

        return dfs(grid, r + 1, c) +
                dfs(grid, r - 1, c) +
                dfs(grid, r, c + 1) +
                dfs(grid, r, c - 1);
    }

    private boolean isInArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length &&
                c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        岛屿的周长 demo = new 岛屿的周长();

        int[][] grid = new int[][] {
                { 1, 1, 1, 1, 0 },
                { 1, 1, 0, 1, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        System.out.println(demo.islandPerimeter(grid));
    }
}
