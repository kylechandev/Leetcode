/*
 * @Author: kaic
 * @Date: 2023-04-27 09:00:05
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-27 09:49:19
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.中等;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 
 * 中等
 * 
 * 
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 
 * 
 * 示例：
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * 
 * 
 * https://leetcode.cn/problems/number-of-islands/description/
 */
public class 岛屿数量 {

    /**
     * DFS
     */
    public int numIslands(char[][] grid) {
        int count = 0;

        int nr = grid.length;
        int nc = grid[0].length;

        // 开始挨个遍历
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == '1') { // 如果是陆地，执行dfs标记周围的陆地（形成整个岛屿）
                    // dfs(grid, i, j);
                    bfs(grid, i, j);

                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        // 边界条件
        if (!isInArea(grid, r, c)) {
            return;
        }

        // 不是岛屿
        if (grid[r][c] != '1') {
            return;
        }

        // 标记已经访问过，避免重复访问
        grid[r][c] = '2';

        // 继续访问 上下左右 四个方向
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    private void bfs(char[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { r, c });

        while (!queue.isEmpty()) {
            int[] land = queue.remove();
            r = land[0];
            c = land[1];

            if (isInArea(grid, r, c) && grid[r][c] == '1') {
                // 标记
                grid[r][c] = '2';

                queue.offer(new int[] { r + 1, c });
                queue.offer(new int[] { r - 1, c });
                queue.offer(new int[] { r, c + 1 });
                queue.offer(new int[] { r, c - 1 });
            }
        }
    }

    private boolean isInArea(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length &&
                c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        岛屿数量 demo = new 岛屿数量();

        char[][] grid = new char[][] {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };

        System.out.println(demo.numIslands(grid));
    }
}
