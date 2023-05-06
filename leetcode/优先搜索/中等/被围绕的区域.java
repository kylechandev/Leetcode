/*
 * @Author: kaic
 * @Date: 2023-05-06 13:53:26
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-06 16:33:04
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.中等;

import java.util.Arrays;

/**
 * 130. 被围绕的区域
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/surrounded-regions/
 */
public class 被围绕的区域 {

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        // 遍历四条边上的每一个值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 只考虑四周边缘
                boolean edge = i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1;
                if (edge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (!isInSafeArea(board, r, c) || board[r][c] != 'O') {
            // 出边界了
            return;
        }

        // 标记这是一个边缘O
        board[r][c] = 'A';

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }

    private boolean isInSafeArea(char[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }

    public static void main(String[] args) {
        被围绕的区域 demo = new 被围绕的区域();

        char[][] board = new char[][] {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'O', 'X' }
        };

        demo.solve(board);

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
