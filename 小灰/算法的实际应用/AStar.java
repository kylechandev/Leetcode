/*
 * @Author: kaic
 * @Date: 2022-11-12 17:03:39
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-12 22:42:38
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.算法的实际应用;

import java.util.ArrayList;
import java.util.List;

/**
 * A星巡路算法 - A*search algorithm
 * 一种用于寻找有效路径的算法
 * 
 * 迷宫巡路问题
 */
public class AStar {

    // 迷宫地图
    public static final int[][] MAZE = {
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 }
    };

    /**
     * A星寻路主逻辑
     * 
     * @param start 迷宫起点
     * @param end   迷宫终点
     */
    public static Node aStarSearch(Node start, Node end) {
        // 可到达节点
        ArrayList<Node> openList = new ArrayList<Node>();
        // 已到达节点
        ArrayList<Node> closeList = new ArrayList<Node>();

        // 首先将起点加入可访问节点
        openList.add(start);

        // 开始遍历路线
        while (!openList.isEmpty()) {
            // 找到当前最短路径的节点
            Node currentNode = findMinGird(openList);
            // 移除
            openList.remove(currentNode);
            // 加入到已到达节点列表中
            closeList.add(currentNode);

            // 找周围节点路径
            List<Node> nodes = findNeighbors(currentNode, openList, closeList);
            // 加入可到达节点
            for (Node node : nodes) {
                node.initGrid(currentNode, end);
                openList.add(node);
            }

            // 如果终点出现在可到达节点，则路径寻找完成
            for (Node node : openList) {
                if (node.x == end.x && node.y == end.y) {
                    // 直接返回终点格子
                    return node;
                }
            }
        }

        return null;
    }

    /**
     * 获取路径最小（f最小）的节点 f=g+h
     */
    private static Node findMinGird(ArrayList<Node> openList) {
        Node result = openList.get(0);

        for (Node node : openList) {
            if (node.f < result.f) {
                result = node;
            }
        }

        return result;
    }

    /**
     * 计算node节点周围的节点
     * 
     * @param node      节点
     * @param openList  可到达节点
     * @param closeList 已到达节点
     * @return
     */
    private static ArrayList<Node> findNeighbors(Node node, List<Node> openList, List<Node> closeList) {
        ArrayList<Node> nodeList = new ArrayList<Node>();

        // 上下左右4个方向

        // 上
        if (isValidNode(node.x, node.y - 1, openList, closeList)) {
            nodeList.add(new Node(node.x, node.y - 1));
        }

        // 下
        if (isValidNode(node.x, node.y + 1, openList, closeList)) {
            nodeList.add(new Node(node.x, node.y + 1));
        }

        // 左
        if (isValidNode(node.x - 1, node.y, openList, closeList)) {
            nodeList.add(new Node(node.x - 1, node.y));
        }

        // 右
        if (isValidNode(node.x + 1, node.y, openList, closeList)) {
            nodeList.add(new Node(node.x + 1, node.y));
        }

        return nodeList;
    }

    /**
     * 判断是否是有效节点（新的节点，未访问过的节点）
     * 
     * @param x         节点坐标x
     * @param y         节点坐标y
     * @param openList  可到达节点
     * @param closeList 已到达节点
     * @return
     */
    private static boolean isValidNode(int x, int y, List<Node> openList, List<Node> closeList) {
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            // 越界
            return false;
        }

        if (MAZE[x][y] == 1) {
            // 障碍物
            return false;
        }

        if (contiansNode(closeList, x, y)) {
            // 遍历过
            return false;
        }

        if (contiansNode(openList, x, y)) {
            // 遍历过
            return false;
        }

        return true;
    }

    /**
     * 判断是否包含某个节点(x,y)
     */
    private static boolean contiansNode(List<Node> nodes, int x, int y) {
        // java8 foreach {
        // 不支持在foreach内部修改外部变量，也不支持在内部foreach中返回函数
        // }
        for (Node node : nodes) {
            if (node.x == x && node.y == y) {
                return true;
            }
        }

        return false;
    }

    /**
     * 迷宫节点
     */
    private static class Node {
        // 节点坐标
        public int x;
        public int y;

        // 从起点走到当前格子的步数，也就是已经花费了多少步
        public int g;
        // 不考虑障碍物，从当前格子走到终点格子的步数
        public int h;
        // g+h
        public int f;

        // 父节点，即他所在路径的上一个节点
        public Node parent;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 标记父亲节点
         * G H F 值
         * 
         * @param parent 父亲节点（上一个节点）
         * @param end    迷宫终点节点
         */
        public void initGrid(Node parent, Node end) {
            this.parent = parent;
            // 从起点走到这个节点所需的步数
            this.g = parent.g + 1;
            // 这个节点无视障碍物到终点节点所需的步数
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            // f=g+h
            this.f = this.g + this.h;
        }
    }

    public static void main(String[] args) {
        // 设置起点和终点
        Node start = new Node(2, 1);
        Node end = new Node(2, 5);

        // 打印迷宫
        System.out.println("迷宫：");
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[i].length; j++) {
                if ((i == start.x && j == end.y) || (i == end.x && j == start.y)) {
                    System.out.print("*, ");
                } else {
                    System.out.print(MAZE[i][j] + ", ");
                }
            }
            System.out.println();
        }

        System.out.println();

        // 搜索到的带路径的迷宫终点
        Node resultNode = aStarSearch(start, end);

        if (resultNode == null) {
            System.out.println("没有找到解谜路径");
            return;
        }

        // 开始回溯解谜路径
        List<Node> path = new ArrayList<Node>();
        while (resultNode != null) {
            path.add(resultNode);
            resultNode = resultNode.parent;
        }

        // 输出解谜路径
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[i].length; j++) {
                if (contiansNode(path, i, j)) {
                    System.out.print("*, ");
                } else {
                    System.out.print(MAZE[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }
}
