/*
 * @Author: kaic
 * @Date: 2023-05-06 22:02:28
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-06 23:53:14
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.中等;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 求走出迷宫的最少步数
 * 求走出迷宫的总方法数
 * 
 * 如果要求输出路径，可以再开一个List来保存每条路径，然后输出最短步数的那条路径（就和`visited`同样的位置）
 */
public class 迷宫 {

    // 最少步数
    private int minSteps = Integer.MAX_VALUE;
    // 总方法数
    private int totalWays = 0;

    // 终点坐标位置
    private int finalX = 0;
    private int finalY = 0;

    // 已访问过的位置坐标 - 0表示未访问过，1表示已访问过
    private int[][] visited;

    /**
     * 计算最少步数 - 注意初始位置处不算步数
     */
    public int minSteps(int[][] maze, int x, int y) {
        if (maze.length == 0) {
            return 0;
        }

        // 确定终点位置
        finalX = x;
        finalY = y;

        // init
        visited = new int[maze.length][maze[0].length];
        // 起点位置标记为已访问
        visited[0][0] = 1;

        dfs(maze, 0, 0, 0);
        System.out.println("总方法数：" + totalWays);

        // bfs(maze, 0, 0);

        return minSteps;
    }

    // 位置数组
    private int[] dx = new int[] { 1, 0, -1, 0 };
    private int[] dy = new int[] { 0, 1, 0, -1 };

    /**
     * https://www.bilibili.com/video/BV1bK4y1C7W2
     * 
     * @param x     当前位置x
     * @param y     当前位置y
     * @param steps 当前已走的步数
     */
    private void dfs(int[][] maze, int x, int y, int steps) {
        // 边界条件
        if (x == finalX && y == finalY) {
            // 已经走到了终点位置
            totalWays++; // 同时记录次数

            if (steps < minSteps) {
                // 更新最少步数
                minSteps = steps;
            }
            return;
        }

        // 处理剪枝（如果要总共走出迷宫的方法数，就不能剪枝）
        if (steps > minSteps) {
            // 当前还未达到终点时，已走的步数已经超过记录的最小步数了，直接剪枝
            return;
        }

        // dfs
        // 顺时针试探每一个位置「右，下，左，上」

        for (int i = 0; i < 4; i++) { // 直接用for循环4个方向
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx >= 0 && tx < maze.length && // 确保x位置合法
                    ty >= 0 && ty < maze[0].length && // 确保y位置合法
                    maze[tx][ty] != -1 && // 确保是可以走的路
                    visited[tx][ty] == 0) { // 确保是没有走过的路
                visited[tx][ty] = 1;
                dfs(maze, tx, ty, steps + 1);
                visited[tx][ty] = 0;
            }
        }

        // // 右
        // if (x + 1 < maze.length && maze[x + 1][y] != -1 && visited[x + 1][y] == 0) {
        // // maze[x][y] == 0 可以走的路
        // // visited[x][y] == 0 这条路还没有走过
        // visited[x + 1][y] = 1;
        // dfs(maze, x + 1, y, steps + 1);
        // visited[x + 1][y] = 0;
        // }

        // // 下
        // if (y + 1 < maze[0].length && maze[x][y + 1] != -1 && visited[x][y + 1] == 0)
        // {
        // // maze[x][y] == 0 可以走的路
        // // visited[x][y] == 0 这条路还没有走过
        // visited[x][y + 1] = 1;
        // dfs(maze, x, y + 1, steps + 1);
        // visited[x][y + 1] = 0;
        // }

        // // 左
        // if (x - 1 >= 0 && maze[x - 1][y] != -1 && visited[x - 1][y] == 0) {
        // // maze[x][y] == 0 可以走的路
        // // visited[x][y] == 0 这条路还没有走过
        // visited[x - 1][y] = 1;
        // dfs(maze, x - 1, y, steps + 1);
        // visited[x - 1][y] = 0;
        // }

        // // 上
        // if (y - 1 >= 0 && maze[x][y - 1] != -1 && visited[x][y - 1] == 0) {
        // // maze[x][y] == 0 可以走的路
        // // visited[x][y] == 0 这条路还没有走过
        // visited[x][y - 1] = 1;
        // dfs(maze, x, y - 1, steps + 1);
        // visited[x][y - 1] = 0;
        // }
    }

    /**
     * https://www.bilibili.com/video/BV16C4y1s7EF
     * 
     * @param x     起点位置x
     * @param y     起点位置y
     * @param steps 当前已走的步数
     */
    private void bfs(int[][] maze, int x, int y) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y, 0)); // 先添加起点位置

        while (!queue.isEmpty()) {
            // 取出队头位置
            Point point = queue.poll();

            if (point.x == finalX && point.y == finalY) {
                // 找到了终点
                minSteps = point.val;
                break;
            }

            // 继续四个方向去扩展
            for (int i = 0; i < 4; i++) { // 直接用for循环4个方向
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];

                if (tx >= 0 && tx < maze.length && // 确保x位置合法
                        ty >= 0 && ty < maze[0].length && // 确保y位置合法
                        maze[tx][ty] != -1 && // 确保是可以走的路
                        visited[tx][ty] == 0) { // 确保是没有走过的路
                    // 加入新的可到达的位置
                    queue.offer(new Point(tx, ty, point.val + 1));
                    visited[tx][ty] = 1; // 标记已访问过（不使用标记是否已访问过，也可以通过，但是耗时会增加上千倍）
                }
            }
        }
    }

    class Point {
        int x;
        int y;
        int val; // 到达这点需要走的步数

        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 0表示可以走的路，-1表示墙
        int[][] maze = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, -1, -1, -1, -1, 0 },
                { 0, 0, 0, -1, 0, 0, 0, 0 },
                { -1, -1, 0, -1, 0, -1, -1, 0 },
                { 0, 0, 0, -1, 0, 0, -1, -1 },
                { 0, 0, -1, -1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, -1, 0, 0 },
                { 0, 0, 0, 0, 0, -1, 0, 0 }
        };

        // int[][] maze = new int[][] {
        // { 0, -1, -1 },
        // { 0, -1, -1 },
        // { 0, 0, 0 }
        // };

        // 指定终点位置
        int finalX = maze.length - 1;
        int finalY = maze[0].length - 1;

        System.out.println("迷宫：");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();

        迷宫 demo = new 迷宫();

        long start = System.currentTimeMillis();
        System.out.println("最少步数" + demo.minSteps(maze, finalX, finalY));
        long end = System.currentTimeMillis();
        System.err.println("耗时：" + (end - start));
    }
}
