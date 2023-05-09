/*
 * @Author: kaic
 * @Date: 2023-05-08 10:53:05
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-08 11:12:10
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.优先搜索.中等;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 365. 水壶问题
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/water-and-jug-problem/description/
 */
public class 水壶问题 {

    private Set<Long> visited = new HashSet<>();

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] { 0, 0 });

        while (!stack.isEmpty()) {
            int[] state = stack.pop();

            if (visited.contains(hash(state))) {
                continue;
            }

            visited.add(hash(state));

            int remainX = state[0];
            int remainY = state[1];

            if (remainX == targetCapacity || remainY == targetCapacity || remainX + remainY == targetCapacity) {
                // 找到了
                return true;
            }

            // 处理六种情况
            // 把 X 壶灌满。
            stack.push(new int[] { jug1Capacity, remainY });
            // 把 Y 壶灌满。
            stack.push(new int[] { remainX, jug2Capacity });
            // 把 X 壶倒空。
            stack.push(new int[] { 0, remainY });
            // 把 Y 壶倒空。
            stack.push(new int[] { remainX, 0 });
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[] { remainX - Math.min(remainX, jug2Capacity - remainY),
                    remainY + Math.min(remainX, jug2Capacity - remainY) });
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[] { remainX + Math.min(remainY, jug1Capacity - remainX),
                    remainY - Math.min(remainY, jug1Capacity - remainX) });

        }

        return false;
    }

    private long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    public static void main(String[] args) {
        水壶问题 demo = new 水壶问题();

        int jug1Capacity = 3;
        int jug2Capacity = 5;
        int targetCapacity = 5;

        System.out.println(demo.canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity));
    }
}
